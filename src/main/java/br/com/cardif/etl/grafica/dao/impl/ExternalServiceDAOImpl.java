package br.com.cardif.etl.grafica.dao.impl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.cardif.etl.grafica.dao.ExternalServiceDAO;
import br.com.cardif.etl.grafica.mapper.ExternalServiceRowMapper;
import br.com.cardif.etl.grafica.pojo.ExternalService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ExternalServiceDAOImpl extends NamedParameterJdbcDaoSupport implements ExternalServiceDAO {

	private static final Logger LOGGER = Logger.getLogger(ExternalServiceDAOImpl.class);

	@Autowired
	public ExternalServiceDAOImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public Integer getSequence(String boneco) {
		StringBuilder sql = new StringBuilder();
			sql.append( " SELECT DISTINCT EXSRV_SEQUENCE FROM EXTERNAL_SERVICE WHERE EXSRV_NM_BONECO = :BONECO");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BONECO", boneco);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}
	
	public String getFileName(String boneco) {
		StringBuilder sql = new StringBuilder();
			sql.append( " SELECT DISTINCT EXSRV_FILE_NAME FROM EXTERNAL_SERVICE WHERE EXSRV_NM_BONECO = :BONECO");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BONECO", boneco);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}
	
	public ExternalService getFileNameSequence(String boneco) {
		StringBuilder sql = new StringBuilder();
			sql.append( " SELECT DISTINCT EXSRV_FILE_NAME, EXSRV_SEQUENCE, exsrv_file_folder FROM  EXTERNAL_SERVICE WHERE EXSRV_NM_BONECO = :BONECO");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BONECO", boneco);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, new ExternalServiceRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}
	
	public ExternalService getExternalService(String boneco) {
		StringBuilder sql = new StringBuilder();
			sql.append( " SELECT * FROM  EXTERNAL_SERVICE WHERE EXSRV_NM_BONECO = :BONECO");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BONECO", boneco);
		LOGGER.error("getExternalService : " + boneco);
		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, new ExternalServiceRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}
	
	public void atualizaExternalService(String boneco) {
		StringBuilder sql = new StringBuilder();
			sql.append( " UPDATE EXTERNAL_SERVICE SET EXSRV_DT_ULTIMA_EXT =:DTATUAL, EXSRV_SEQUENCE = (EXSRV_SEQUENCE +1) WHERE EXSRV_NM_BONECO = :BONECO");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BONECO", boneco);
		paramSource.addValue("DTATUAL", new Date());

		try {
			getNamedParameterJdbcTemplate().update(sql.toString(), paramSource);
		} catch (DataAccessException e) {
			throw e;
		}
	}

	public Date getDtUltimaExtracao(String boneco) {
		StringBuilder sql = new StringBuilder();
		sql.append( " SELECT EXSRV_DT_ULTIMA_EXT FROM EXTERNAL_SERVICE WHERE EXSRV_NM_BONECO = :BONECO");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("BONECO", boneco);

		try {
			return getNamedParameterJdbcTemplate().queryForObject(sql.toString(), paramSource, Date.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}


	public List<String> getBonecosForExtract(){
		StringBuilder sql = new StringBuilder();
		sql.append( " SELECT EXSRV_NM_BONECO FROM EXTERNAL_SERVICE WHERE EXSRV_ENABLE = :ENABLE ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("ENABLE", 'N');

		try {
			return getNamedParameterJdbcTemplate().queryForList(sql.toString(), paramSource, String.class);

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw e;
		}
	}
}
