package br.com.cardif.etl.grafica.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.cardif.etl.grafica.vo.CorretorVO;
import br.com.cardif.etl.grafica.dao.ParticipantesDAO;
import br.com.cardif.etl.grafica.mapper.BeneficiarioRowMapper;
import br.com.cardif.etl.grafica.mapper.CorretorRowMapper;
import br.com.cardif.etl.grafica.mapper.EstipulanteRowMapper;
import br.com.cardif.etl.grafica.mapper.SubestipulanteRowMapper;
import br.com.cardif.etl.grafica.pojo.Beneficiario;
import br.com.cardif.etl.grafica.pojo.Corretor;
import br.com.cardif.etl.grafica.pojo.Estipulante;
import br.com.cardif.etl.grafica.pojo.Subestipulante;
import br.com.cardif.etl.grafica.vo.BeneficiarioVO;
import br.com.cardif.etl.grafica.vo.CoberturaVO;
import br.com.cardif.etl.grafica.vo.SubestipulanteVO;
import br.com.cardif.etl.jobs.spring.HashMapRowMapper;

@Repository
public class ParticipantesDAOImpl extends NamedParameterJdbcDaoSupport implements ParticipantesDAO {

	private static final Logger LOGGER = Logger.getLogger(ParticipantesDAOImpl.class);

	@Autowired
	public ParticipantesDAOImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public Map<Integer, BeneficiarioVO> buscaBeneficiarios(Integer certificado, Integer endosso) {
		Map<Integer, BeneficiarioVO> map = new HashMap<Integer, BeneficiarioVO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT     																			           			")
			.append("   ROW_NUMBER() OVER(PARTITION BY PCB.POLICY_NO ORDER BY PCB.POLCRTBENEF_PERC_SHARING DESC) AS ID,  ")
			.append(" 	PCB.POLCRTBENEF_PERC_SHARING[PERCENTUAL],     												                    ")
			.append(" 	PCB.POLCRTBENEF_NM[BENEFICIARIO],     														                    ")
			.append(" 	CONVERT(VARCHAR(8), ISNULL(PCB.POLCRTBENEF_BIRTH_DT,'1970-01-01'), 112)[NASCIMENTO_BENEFICIARIO],               ")
			.append(" 	R.RELAT_DS [GRAU_PARENTESCO]     															                    ")
			.append(" FROM POLICY_CERTIFICATE_BENEFICIARY PCB                                                                           ")
			.append(" INNER JOIN RELATIVE R ON PCB.RELAT_CD = R.RELAT_CD                                                                ")
			.append(" WHERE PCB.POLICY_NO = :CERTIFICADO AND PCB.POLCRT_ENDORS_NO =:ENDOSSO								                ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado);
		paramSource.addValue("ENDOSSO", endosso);

		try {
			List<BeneficiarioVO> lista =  getNamedParameterJdbcTemplate().query(sql.toString(), paramSource, new BeneficiarioRowMapper());
			for(BeneficiarioVO vo : lista) {
				map.put(vo.getId(), vo);
			}
			return map;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("buscaBeneficiarios: empty result: " + certificado);
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public Estipulante buscaEstipulante(Integer certificado, Integer endosso) {
		StringBuilder sql = new StringBuilder();
			 sql.append(" SELECT DISTINCT                     																	")
				.append("	P.PARTNER_NM[ESTIPULANTE],                  														")
				.append("	P.PARTNER_CGC[CNPJ_ESTIPULANTE],              														")
				.append("	CONVERT(DECIMAL(3,1), MPB.MPPROL_CMS_PERC)[PERCENTUAL_PROLABORE_ESTIPULANTE],      					")
				.append("	CAST(ISNULL(ROUND((PCX.PAYEND_DUE_VL / 																")
				.append(" 		(1 + ((SELECT IOF_PERCENT_VL FROM IOF_INSURANCE_SECTOR WHERE IOF_EXP_DTM IS NULL) / 100))) 		")
				.append("		* (MPB.MPPROL_CMS_PERC /100),2),0.0)AS MONEY) [VALOR_PROLABORE_ESTIPULANTE]          			")
				.append(" FROM POLICY_CERTIFICATE C                                                       						")
				.append(" INNER JOIN MASTER_POLICY MP ON(C.MP_NO = MP.MP_NO AND C.MP_ENDORS_NO = MP.MP_ENDORS_NO)            	")
				.append(" INNER JOIN PARTNER P ON (P.PARTNER_ID = MP.PARTNER_ID)            									")
				.append(" LEFT JOIN MASTER_POLICY_PROLABORE MPB ON (MPB.MP_NO = C.MP_NO AND MPB.MP_ENDORS_NO = C.MP_ENDORS_NO ) ")
				.append(" INNER JOIN POLICY_CERTIF_PAYMENT_ENDORS PCX ON(PCX.POLICY_NO  = C.POLICY_NO 							")
				.append("	AND PCX.POLCRT_ENDORS_NO = C.POLCRT_ENDORS_NO AND PCX.PAYEND_DEL_FG = 'N') 							")
				.append(" where C.policy_no = :CERTIFICADO AND C.POLCRT_ENDORS_NO =:ENDOSSO										");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado);
		paramSource.addValue("ENDOSSO", endosso);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, new EstipulanteRowMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("buscaEstipulante: empty result: " + certificado);
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public Map<Integer, SubestipulanteVO> buscaSubestipulante(Integer certificado, Integer endosso) {
		Map<Integer, SubestipulanteVO> map = new HashMap<Integer, SubestipulanteVO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT 																													")
			.append("	ROW_NUMBER() OVER(PARTITION BY PCA.POLICY_NO ORDER BY PCA.PCAGENT_CMS_PERC DESC) AS ID,  										")
			.append("	PCA.AGENT_ID,            																										")
			.append("	AG.AGENT_NM [SUBESTIPULANTE],            																						")
			.append("	AG.AGENT_SOCIAL_SEC_NO [SUBESTIPULANTE_CNPJ],               																	")
			.append("	MPBA.MPBROKERAG_CMS_PERC [PERCENTUAL_PROLABORE_SUBESTIPULANTE],																	")
			.append("	ROUND(((SUM(PCX.PCPRCPLN_PREMIUM_VL) * MPBA.MPBROKERAG_CMS_PERC)/100) , 2, 1) [VALOR],											")
			.append("	AG.AGENT_ADDR_DS [ENDERECO],																									")
			.append("	'' [COMPLEMENTO],																												")
			.append("	'' [BAIRRO],																													")
			.append("	AG.AGENT_CITY_NM [CIDADE],																										")
			.append("   AG.AGENT_STATE_AC [ESTADO],																										")
			.append("   AG.AGENT_POST_CD [CEP],																											")
			.append("   AG.AGENT_PHONE_AREA_CD [DDD],																									")
			.append("   AG.AGENT_PHONE_NO [TELEFONE]																									")
			.append(" FROM POLICY_CERTIFICATE_AGENT PCA 																								")
			.append(" INNER JOIN AGENT AG ON (AG.AGENT_ID = PCA.AGENT_ID AND AG.AGENT_DEL_FG = 'N') 													")
			.append(" INNER JOIN MASTER_POLICY_BROKER_AGENT MPBA ON (MPBA.AGENT_ID = PCA.AGENT_ID AND MPBA.MPBROKERAG_DEL_FG = 'N')						")
			.append(" INNER JOIN PC_PROD_RSK_COVRGE_PLANS PCX ON(PCX.POLICY_NO  = PCA.POLICY_NO AND PCX.POLCRT_ENDORS_NO = PCA.POLCRT_ENDORS_NO )     	")               
			.append(" WHERE PCA.POLICY_NO =:CERTIFICADO	AND PCA.POLCRT_ENDORS_NO =:ENDOSSO																")	
			.append(" GROUP BY PCA.POLICY_NO,																											")
			.append(" 	PCA.AGENT_ID,         																											")   
			.append(" 	AG.AGENT_NM,    																												")        
			.append("   AG.AGENT_SOCIAL_SEC_NO,      																									")      
			.append("   MPBA.MPBROKERAG_CMS_PERC,																										")
			.append("   PCX.PCPRCPLN_PREMIUM_VL , 																										")
			.append("   AG.AGENT_ADDR_DS ,																												")
			.append("   AG.AGENT_CITY_NM ,																												")
			.append("   AG.AGENT_STATE_AC ,																												")
			.append("   AG.AGENT_POST_CD ,																												")
			.append("   AG.AGENT_PHONE_AREA_CD ,																										")
			.append("   AG.AGENT_PHONE_NO ,																												")
			.append("   PCA.PCAGENT_CMS_PERC																											");
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado);
		paramSource.addValue("ENDOSSO", endosso);

		try {
			List<SubestipulanteVO> lista =  getNamedParameterJdbcTemplate().query(sql.toString(), paramSource, new SubestipulanteRowMapper());
			for(SubestipulanteVO vo : lista) {
				map.put(vo.getId(), vo);
			}
			return map;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("buscaEstipulante: empty result: " + certificado);
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public Map<Integer, CorretorVO> buscaCorretores(Integer certificado, Integer endosso){
		Map<Integer, CorretorVO> map = new HashMap<Integer, CorretorVO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT                     							                      ")
			.append(" ROW_NUMBER() OVER(PARTITION BY PC.POLICY_NO ORDER BY PC.POLICY_NO DESC) AS ID , ")
			.append(" B.BROKER_NM [NOME],            						                          ")
			.append(" B.BROKER_SOCIAL_SEC_NO[CNPJ],            			                              ")
			.append(" REPLACE(REPLACE(B.BROKER_SUSEP_NO,'.',''),'-','') [REGISTRO_SUSEP],             ")
			.append(" B.BROKER_ADDR_DS [LOGRADOURO],       				                              ")
			.append(" ''[COMPLEMENTO],            							                          ")
			.append(" ''[BAIRRO],            								                          ")
			.append(" B.BROKER_CITY_NM[CIDADE],           				 	                          ")
			.append(" B.BROKER_STATE_AC[ESTADO],            				                          ")
			.append(" B.BROKER_POST_CD[CEP],            					                          ")
			.append(" ISNULL(B.BROKER_PHONE_AREA_CD,'00')[DDD],      		                          ")
			.append(" ISNULL(B.BROKER_PHONE_NO,'0000000000')[TELEFONE]   	                          ")
			.append(" FROM POLICY_CERTIFICATE PC             						                  ")
			.append(" INNER JOIN MASTER_POLICY_BROKER_AGENT MPBA ON (MPBA.MP_NO = PC.MP_NO 			  ")
			.append(" 	AND MPBA.MP_ENDORS_NO = PC.MP_ENDORS_NO AND MPBA.MPBROKERAG_DEL_FG = 'N')     ")
			.append(" INNER JOIN BROKER B ON (B.BROKER_ID = MPBA.BROKER_ID)  		                  ")
			.append(" where PC.POLICY_NO = :CERTIFICADO AND PC.POLCRT_ENDORS_NO =:ENDOSSO             ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado);
		paramSource.addValue("ENDOSSO", endosso);

		try {
			List<CorretorVO> lista =  getNamedParameterJdbcTemplate().query(sql.toString(), paramSource, new CorretorRowMapper());
			for(CorretorVO vo : lista) {
				map.put(vo.getId(), vo);
			}
			return map;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("buscaCorretores: empty result: " + certificado);
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}
}
