package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.pojo.Estipulante;


public class EstipulanteRowMapper implements RowMapper<Estipulante> {

	private static final Logger LOGGER = Logger.getLogger(EstipulanteRowMapper.class);

	@Override
	public Estipulante mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estipulante estipulante = new Estipulante();
		estipulante.setNome(rs.getString("ESTIPULANTE"));
		estipulante.setCnpj(rs.getString("CNPJ_ESTIPULANTE"));
		estipulante.setPercentProLabore(rs.getDouble("PERCENTUAL_PROLABORE_ESTIPULANTE"));
		estipulante.setValorProlabore(rs.getDouble("VALOR_PROLABORE_ESTIPULANTE"));
		return estipulante;
	}
}
