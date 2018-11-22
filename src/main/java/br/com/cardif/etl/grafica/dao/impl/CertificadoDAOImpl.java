package br.com.cardif.etl.grafica.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import br.com.cardif.etl.grafica.mapper.*;
import br.com.cardif.etl.grafica.pojo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.cardif.etl.grafica.dao.CertificadoDAO;
import br.com.cardif.etl.grafica.vo.CoberturaVO;

@Repository
public class CertificadoDAOImpl extends NamedParameterJdbcDaoSupport implements CertificadoDAO {

	private static final Logger LOGGER = Logger.getLogger(CertificadoDAOImpl.class);

	@Autowired
	public CertificadoDAOImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public List<DadosSegurado> buscaDadosSegurado(String boneco){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT																														")
			.append("	 ES.EXSRV_NM_BONECO [BONECO],																										")
			.append("	 ES.EXSRV_PRD_TYPE [TIPOPRODUTO],																									")
			.append("	 C.CLIENT_NM [NOME_SEGURADO],																										")
			.append("	 C.CLIENT_SSN_NBR [CPF_CNPJ],																										")
			.append("	 MP.PARTNER_ID [ID_PARCEIRO],																										")
			.append("	 PC.MP_NO[APOLICE],																													")
			.append("	 PC.MP_ENDORS_NO [ENDOSSO_APOLICE],																									")
			.append("	 MP.MP_PARENT_NO[APOLICE_VARIAVEL],																									")
			.append("	 C.CLIENT_BIRTH_DT [DTNASCIMENTO],																									")
			.append("	 ISNULL(C.CLIENT_GENDER_CD,'M')[SEXO],																								")
			.append("	 ISNULL(CA.CLNADDR_STREET_NM,'') +' , '+ ISNULL(CA.CLNADDR_STREET_NO,'') [LOGRADOURO],												")
			.append("	 CA.CLNADDR_STREET_CP [COMPLEMENTO],																								")
			.append("	 CA.CLNADDR_BRANCH_NM [BAIRRO],																										")
			.append("	 CA.CLNADDR_CITY_NM[CIDADE],																										")
			.append("	 CA.CLNADDR_STATE_AC  [ESTADO],																										")
			.append("	 CA.CLNADDR_POST_CD[CEP],																											")
			.append("	 C.CLIENT_EMAIL_NM [EMAIL],																											")
			.append("	 C.CLIENT_EMAIL_NM [EMAIL_COMPLEMENTAR],																							")
			.append("	 NACIONALIDADE.PCOI_DESC [NACIONALIDADE],																							")
			.append("	 PC.POLCRT_KEYWORD_ID [CONTRATO],																									")
			.append("	 PC.POLCRT_ENDORS_NO [ENDOSSO],																										")
			.append("	 PC.POLICY_NO [CERTIFICADO],																										")
			.append("	 (PC.POLCRT_EFF_DTM - 1) [DATA_ADESAO],																								")
			.append("	 PC.POLCRT_EFF_DTM [INICIO_VIGENCIA],																								")
			.append("	 PC.POLCRT_EXP_DTM [FIM_VIGENCIA],																									")
			.append("	 POLCRT_PARTNER_PRODUCT_DS ,																										")
			.append("	 PC.POLCRT_FINANCED_VL [VALOR_FINANCIADO],																							")
			.append("	 PCC.PCCREC_CREDIT_CARD_NO [NUMERO_CARTAO_SEGURADO],																				")
			.append("	 PCR.LOC_RISC_ADR + ', ' + PCR.LOC_RISC_ADR_NBR [ENDERECO_RISCO],																	")
			.append("	 PCR.LOC_RISC_ADR_CMP [COMPLEMENTO_RISCO],																							")
			.append("	 PCR.LOC_RISC_NBH [BAIRRO_RISCO],																									")
			.append("	 PCR.LOC_RISC_CTY [CIDADE_RISCO],																									")
			.append("	 PCR.LOC_RISC_STE [ESTADO_RISCO],																									")
			.append("	 PCR.LOC_RISC_ZIP [CEP_RISCO]																										")
			.append("	FROM POLICY_CERTIFICATE PC																											")
			.append("	INNER JOIN CLIENT C ON C.CLIENT_NO = PC.CLIENT_NO AND C.CLIENT_DEL_FG = 'N'															")
			.append("	INNER JOIN MASTER_POLICY MP ON MP.MP_NO = PC.MP_NO AND MP.MP_ENDORS_NO = PC.MP_ENDORS_NO AND MP.MP_DEL_FG = 'N'						")
			.append("	INNER JOIN CLIENT_ADDRESS CA ON CA.CLIENT_NO = C.CLIENT_NO AND CA.CLNADDR_DEL_FG = 'N' AND CA.CLNADDR_MAIL_ADDRESS_FG = 'S'			")
			.append("	LEFT JOIN POLICY_CERTIF_CREDIT_CARD PCC ON (PCC.POLICY_NO = PC.POLICY_NO AND PCC.POLCRT_ENDORS_NO = PC.POLCRT_ENDORS_NO)			")
			.append("	LEFT JOIN POLICY_CERTIF_PAYMENT_ENDORS PCPE ON PCPE.POLICY_NO = PC.POLICY_NO AND PCPE.POLCRT_ENDORS_NO = PC.POLCRT_ENDORS_NO		")
			.append("		AND PCPE.PAYEND_DEL_FG = 'N' AND PCPE.PAYEND_INSTMNT_NO = 1 AND PCPE.PAYEND_DUE_VL > 0.0										")
			.append("	LEFT JOIN POLICY_CERTIF_INSURED_OBJECT_RES PCR ON PCR.POLICY_NO = PC.POLICY_NO														")
			.append("	INNER JOIN MP_EXTERNAL_SERVICE MES ON MES.MP_NO = MP.MP_NO AND MES.MP_ENDORS_NO = MP.MP_ENDORS_NO									")
			.append("	INNER JOIN EXTERNAL_SERVICE ES ON ES.EXSRV_ID = MES.EXSRV_ID AND ES.EXSRV_DEL_FG = 'N' AND ES.EXSRV_ENABLE = 'N'					")
			.append("	OUTER APPLY(																														")
			.append("		SELECT PCOI_DESC																												")
			.append("		FROM POLICY_CERTIF_OTHER_INF PCOI																								")
			.append("		INNER JOIN POLICY_CERTIF_OTHER_INF_DOMAIN PCOID ON PCOI.PCOID_ID = PCOID.PCOID_ID AND PCOID_NAME = 'NACIONALIDADE'				")
			.append("		WHERE PCOI.POLICY_NO = PC.POLICY_NO AND PCOI.POLCRT_ENDORS_NO = PC.POLCRT_ENDORS_NO												")
			.append("	) NACIONALIDADE																														")
			.append("	CROSS APPLY (																														")
			.append("		SELECT MAX(XIN.CLNADDR_ID) MAXID																								")
			.append("		FROM CLIENT_ADDRESS XIN																											")
			.append("		WHERE XIN.CLIENT_NO = C.CLIENT_NO																								")
			.append("		AND  XIN.CLNADDR_DEL_FG = 'N'																									")
			.append("		AND XIN.CLNADDR_MAIL_ADDRESS_FG = 'S')  MAXADDR																					")
			.append("	CROSS APPLY (SELECT MAX(CERTIF.POLCRT_ENDORS_NO) ENDOSSO FROM POLICY_CERTIFICATE CERTIF												")
			.append("					WHERE CERTIF.POLICY_NO = PC.POLICY_NO AND CERTIF.POLCRT_DEL_FG = 'N') CERTIF_ENDOSSO								")
			.append("	WHERE CA.CLNADDR_ID = MAXADDR.MAXID																									")
			.append("		AND ES.EXSRV_NM_BONECO = :BONECO																								")
			.append("		AND PC.POLCRT_ENDORS_NO = CERTIF_ENDOSSO.ENDOSSO																				")
			.append("		AND PC.POLCRT_DEL_FG = 'N'																										")
			.append("		AND PC.POLST_ID = 1																												")
			.append("		AND PC.POLCRT_EXP_DTM > GETDATE()																								")
			.append("		AND PC.POLCRT_LETTER_EXPORT_DTM IS NULL																							")
			.append("		AND PC.POLCRT_UPD_DTM BETWEEN ES.EXSRV_DT_ULTIMA_EXT AND GETDATE()																")
			.append("	ORDER BY C.CLIENT_NM																												");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BONECO", boneco);

		try {
			return getNamedParameterJdbcTemplate().query(sql.toString(), paramSource, new DadosSeguradoRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public Parcela buscaParcela(Integer certificado, Integer endossoCertif) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT                                               										")
			.append(" 	PAY.PAYEND_DUE_VL     [VALOR_PARCELA],                                                	")
			.append(" 	SUM(PAY.PAYEND_DUE_VL)[VALOR_TOTAL],              	                                	")
			.append(" 	MIN(PAY.PAYEND_INSTMNT_NO)[PARCELA_INICIAL],        	                                ")
			.append(" 	MAX(PAY.PAYEND_INSTMNT_NO)[TOTAL_PARCELAS],             	                            ")
			.append(" 	PAY.PAYEND_IOF_VL [IOF],              	                                				")
			.append("   100 [LMI_PERCENTUAL_COBERTURA_1]            	                                		")
			.append(" FROM POLICY_CERTIF_PAYMENT_ENDORS PAY 													")
			.append(" WHERE PAY.POLICY_NO = :CERTIFICADO                                                        ")
			.append("  AND PAY.POLCRT_ENDORS_NO = :ENDOSSO                                                      ")
			.append("  GROUP BY PAY.PAYEND_DUE_VL, PAY.PAYEND_DUE_VL, PAY.PAYEND_INSTMNT_NO, PAY.PAYEND_IOF_VL 	");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado);
		paramSource.addValue("ENDOSSO", endossoCertif);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, new ParcelaRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public Capitalizacao buscaCapitalizacao(Integer certificado) {
		//TODO - verificar o calculo de IR
		StringBuilder sql = new StringBuilder();
		sql.append( " SELECT DISTINCT            	                                                                    	")
			.append(" 	CAP.CAP_LTT [SERIE],            	                                                            ")
			.append(" 	CONVERT (INT, CAP.CAP_NBR) [NUMERO_SORTE],            	                                        ")
			.append(" 	CASE            	                                                                            ")
			.append(" 		WHEN CSC.CAP_PERIOD = 0 THEN 'ACOMPANHA VIGÊNCIA CERTIFICADO'            	                ")
			.append(" 		WHEN CSC.CAP_PERIOD > 0 THEN CAST(CSC.CAP_PERIOD AS VARCHAR)+ ' MESES'             	        ")
			.append(" 	END [CAP_VIGENCIA],            	                                                                ")
			.append(" 	CASE																							")
			.append(" 	    WHEN CSC.CAP_PERIOD = 1 THEN 'ÚNICO'														")
			.append(" 	    WHEN CSC.CAP_PERIOD <> 1 THEN 'MENSAL'														")
			.append("	END [CAP_PERIODICIDADE],            	                                             			")
			.append(" 	CAP.CAP_PREMIUM [CAP_PREMIO_BRUTO],            	                                                ")
			.append(" 	CAST((ROUND(CAP.CAP_PREMIUM - (CAP.CAP_PREMIUM * 0.2500),2))AS MONEY) [CAP_PREMIO_LIQUIDO],  	")
			.append(" 	25.0 [CAP_IR]      																				")
			.append(" FROM  DBCAPREGISTRY.DBO.CAPITAL_SERIES_CARDIF CAP   												")
			.append(" INNER JOIN DBCAPREGISTRY.DBO.SYSTEM_KEY_CONFIG CSC ON (CSC.CAP_KEY_ID = CAP.CAP_KEY_ID)  			")
			.append(" WHERE CAP.POLICY_NO = :CERTIFICADO	                                                            ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado.toString());

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource,
					new CapitalizacaoRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public Double buscaFactorParaCalculo(Integer certificado, Integer endosso) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SUM(PCA.PCPRCPLN_PREMIUM_FACTOR) 	")
			.append(" FROM PC_PROD_RSK_COVRGE_PLANS PCA			")
			.append(" WHERE PCA.POLICY_NO = :CERTIFICADO  		")
			.append(" 	AND PCA.POLCRT_ENDORS_NO = :ENDOSSO)	");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado);
		paramSource.addValue("ENDOSSO", endosso);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, Double.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public List<CoberturaVO> buscaCoberturas(String contrato) {
		StringBuilder sql = new StringBuilder();
		sql.append( " SELECT																																")
			.append("   A.PLANO,																															")
			.append("   SUM(A.PREMIO_COBERTURA)[PREMIO_COBERTURA],																							")
			.append("   A.LMI_PERCENTUAL,																													")
			.append("	MAX(A.PCPRC_INSSURED_AMOUNT) [LMI_COBERTURA],																						")
			.append("   A.COVRGE_INSTMNT_QTY [LMI_PARCELA],																									")
			.append("   MPPRG.MPPRCP_AGROUP_GFC [ID]																										")
			.append(" FROM MASTER_POLICY_PROD_RSK_COVRGE MPPRG																								")
			.append(" CROSS APPLY(SELECT DISTINCT																											")
			.append("               P.PLAN_DS[PLANO],																										")
			.append("               ISNULL(PCA.PCPRCPLN_PREMIUM_VL,																							")
			.append("               ISNULL(PC.POLCRT_FINANCED_VL,0.0) * ISNULL(PCA.PCPRCPLN_PREMIUM_FACTOR,0.0))[PREMIO_COBERTURA],							")
			.append("               MCR.CLMRLS_PCT_RSV_INL_CLM [LMI_PERCENTUAL],																			")
			.append("               R.RISK_ID,																												")
			.append("               PC.MP_NO,																												")
			.append("               PC.MP_ENDORS_NO,																										")
			.append("               PCCB.PCPRC_INSSURED_AMOUNT,																								")
			.append("               CVG.COVRGE_INSTMNT_QTY																									")
			.append("             FROM POLICY_CERTIFICATE PC																								")
			.append("             INNER JOIN PC_PROD_RSK_COVRGE_PLANS PCA ON (PCA.POLICY_NO = PC.POLICY_NO AND PCA.POLCRT_ENDORS_NO = PC.POLCRT_ENDORS_NO)	")
			.append("             INNER JOIN PLANS P ON(P.PLAN_ID = PCA.PLAN_ID)																			")
			.append("             INNER JOIN COVERAGE CVG ON(CVG.COVRGE_ID = PCA.COVRGE_ID)																	")
			.append("             INNER JOIN RISK R ON (R.RISK_ID = PCA.RISK_ID)																			")
			.append("             INNER JOIN MP_CLAIM_RULES MCR ON MCR.MP_NO = PC.MP_NO AND MCR.MP_ENDORS_NO = PC.MP_ENDORS_NO								")
			.append("             INNER JOIN POLICY_CERTIF_PROD_RSK_COVRGE PCCB ON (PCCB.POLICY_NO = PCA.POLICY_NO											")
			.append("               AND PCCB.POLCRT_ENDORS_NO = PCA.POLCRT_ENDORS_NO																		")
			.append("               AND PCCB.RISK_ID = PCA.RISK_ID AND PCCB.COVRGE_ID = PCA.COVRGE_ID)														")
			.append("             WHERE PC.POLCRT_KEYWORD_ID = :CONTRATO ) AS A																				")
			.append(" WHERE MPPRG.MP_NO = A.MP_NO																											")
			.append(" AND MPPRG.MP_ENDORS_NO = A.MP_ENDORS_NO																								")
			.append(" AND MPPRG.RISK_ID = A.RISK_ID																											")
			.append(" GROUP BY A.PLANO,	A.LMI_PERCENTUAL, MPPRG.MPPRCP_AGROUP_GFC, A.COVRGE_INSTMNT_QTY														");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CONTRATO", contrato);

		try {
			return getNamedParameterJdbcTemplate().query(sql.toString(), paramSource, new CoberturaRowMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("buscaCoberturas: empty result: " + contrato);
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public Veiculo buscaVeiculo(Integer certificado) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT                                          								")
			.append(" 	FIP.FIPE_BRAND_CAR,                      											")
			.append(" 	FIP.FIPE_MODEL_CAR,                      											")
			.append(" 	CAR.FIPE_COD,                  														")
			.append(" 	CAR.COI_CAR_MODEL_YEAR,             												")
			.append(" 	CAR.COI_CAR_LICENSE_PLATE,              											")
			.append(" 	CAR.COI_CAR_CHASSI_NUMEBR,        													")
			.append(" 	CAR.COI_CAR_RENAVAM_NUMBER,             											")
			.append(" 	CAR.COI_CAR_FUEL ,                   												")
			.append(" 	COI_CAR_FIPE_ZEROKM ,             	      											")
			.append(" 	CC.COLOR_DSC,   	      															")
			.append(" 	PCIO.LOC_RISC_CTY + ' ' + PCIO.LOC_RISC_STE [CIRCULACAO]							")
			.append(" FROM CAR_OBJ_INSURED CAR           		              								")
			.append(" INNER JOIN CAR_FIPE FIP ON(CAR.FIPE_COD = FIP.FIPE_COD) 								")
			.append(" LEFT JOIN CAR_COLOR CC ON(CC.COLOR_COD = CAR.COLOR_COD) 								")
			.append(" LEFT JOIN POLICY_CERTIF_INSURED_OBJECT_RES PCIO ON(PCIO.POLICY_NO = CAR.POLICY_NO) 	")
			.append(" WHERE CAR.POLICY_NO = :CERTIFICADO                      								");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, new VeiculoRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public Produto buscaProduto(Integer certificado, Integer endosso) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT TOP 1 																									    ")
				.append(" 	PW.PRODWAR_OUTOFFICE_CD [CODIGO_PRODUTO], 																				")
				.append(" 	PW.PRODWAR_NM [MARCA_PRODUTO], 																							")
				.append(" 	PW.PRODWAR_CARDIF_BRAND [MODELO_PRODUTO],  																				")
				.append(" 	PW.PRODWAR_DS [DESCRICAO_PRODUTO],  																					")
				.append(" 	PCOI.PCOI_DESC [IMEI], 																									")
				.append(" 	C.POLCRT_PARTNER_PRODUCT_DS [PRODUCT_DS],  																				")
				.append(" 	PW.PARTNER_ID 																											")
				.append(" FROM POLICY_CERTIFICATE C(NOLOCK)    																						")
				.append(" INNER JOIN MASTER_POLICY MP ON MP.MP_NO = C.MP_NO AND MP.MP_ENDORS_NO = C.MP_ENDORS_NO 									")
				.append(" INNER JOIN PRODUCT_WARRANTY PW ON PW.PRODWAR_OUTOFFICE_CD = C.POLCRT_PARTNER_PRODUCT_DS AND PW.PARTNER_ID = MP.PARTNER_ID ")
				.append(" INNER JOIN POLICY_CERTIF_OTHER_INF PCOI ON PCOI.POLICY_NO = C.POLICY_NO AND PCOI.POLCRT_ENDORS_NO = C.POLCRT_ENDORS_NO 	")
				.append(" INNER JOIN POLICY_CERTIF_OTHER_INF_DOMAIN PCOID ON PCOI.PCOID_ID = PCOID.PCOID_ID AND PCOID_NAME = 'IMEI' 				")
				.append(" WHERE C.POLICY_NO = :CERTIFICADO AND C.POLCRT_ENDORS_NO =:ENDOSSO				 									    ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("CERTIFICADO", certificado);
		paramSource.addValue("ENDOSSO", certificado);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, new ProdutoRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public void atualizaCertificadosExtraidos(String boneco) {
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE POLICY_CERTIFICATE SET POLCRT_LETTER_EXPORT_DTM = GETDATE(), 														")
			.append(" POLCRT_LETTER_EXPORT_COUNT = ISNULL(POLCRT_LETTER_EXPORT_COUNT, 0) + 1  													")
			.append(" WHERE POLICY_NO IN (SELECT POLICY_NO FROM POLICY_CERTIFICATE PC					                						")
			.append(" 						INNER JOIN MP_EXTERNAL_SERVICE MES ON MES.mp_no = PC.mp_no AND MES.MP_ENDORS_NO = PC.MP_ENDORS_NO	")
			.append(" 						INNER JOIN EXTERNAL_SERVICE ES ON ES.EXSRV_ID = MES.EXSRV_ID										")
			.append(" 						WHERE ES.EXSRV_NM_BONECO = :BONECO																	")
			.append(" 						AND PC.POLCRT_DEL_FG = 'N'																			")
			.append(" 						AND PC.POLST_ID = 1																					")
			.append(" 						AND PC.POLCRT_EXP_DTM > GETDATE() 						                                        	")
			.append(" 						AND PC.POLCRT_LETTER_EXPORT_DTM IS NULL					                                        	")
			.append(" 						AND PC.POLCRT_UPD_DTM BETWEEN ES.EXSRV_DT_ULTIMA_EXT AND GETDATE())									");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BONECO", boneco);

		try {
			getNamedParameterJdbcTemplate().update(query.toString(), paramSource);
		} catch (DataAccessException e) {
			throw e;
		}
	}
}
