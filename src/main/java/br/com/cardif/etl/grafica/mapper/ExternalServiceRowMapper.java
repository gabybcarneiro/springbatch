package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.pojo.ExternalService;

public class ExternalServiceRowMapper implements RowMapper<ExternalService> {

	private static final Logger LOGGER = Logger.getLogger(ExternalServiceRowMapper.class);

	@Override
	public ExternalService mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ExternalService externalService = new ExternalService();
		externalService.setId(rs.getInt("EXSRV_ID"));
		externalService.setBoneco(rs.getString("exsrv_nm_boneco"));
		externalService.setTipoProduto(rs.getInt("exsrv_prd_type"));
		externalService.setSequence(rs.getLong("exsrv_sequence"));
		externalService.setFileName(rs.getString("exsrv_file_name"));
		externalService.setDtAtualizacao(rs.getDate("EXSRV_UPD_DTM"));
		externalService.setFileFolder(rs.getString("exsrv_file_folder"));
		return externalService;
	}
}
