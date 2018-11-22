package br.com.cardif.etl.grafica.dao.impl;

import br.com.cardif.etl.grafica.dao.ExternalServiceParamDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ExternalServiceParamDAOImpl extends NamedParameterJdbcDaoSupport implements ExternalServiceParamDAO {

	private static final Logger LOGGER = Logger.getLogger(ExternalServiceParamDAOImpl.class);

	@Autowired
	public ExternalServiceParamDAOImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}


	@Override
	public Map<String, Object> buscaValoresPadrao(String boneco) {
		final HashMap<String,Object> mapRet= new HashMap<String,Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(  " SELECT PARAM_CHAVE, PARAM_VALOR								")
			.append( " FROM EXTERNAL_SERVICE_PARAMETRO ESP							")
			.append( " INNER JOIN EXTERNAL_SERVICE ES ON ES.EXSRV_ID = ESP.EXSRV_ID	")
			.append( " WHERE ES.EXSRV_NM_BONECO = :boneco							");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("boneco", boneco);

		try {
			getNamedParameterJdbcTemplate().query(sql.toString(), paramSource, new ResultSetExtractor<Map>(){
					@Override
					public Map extractData(ResultSet rs) throws SQLException,DataAccessException {
						while(rs.next()){
							mapRet.put(rs.getString("PARAM_CHAVE"),rs.getString("PARAM_VALOR"));
						}
						return mapRet;
					}
				});
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}

		return mapRet;
	}
}
