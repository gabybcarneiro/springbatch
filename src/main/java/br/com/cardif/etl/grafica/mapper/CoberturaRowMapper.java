package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.vo.CoberturaVO;

public class CoberturaRowMapper implements RowMapper<CoberturaVO> {

	private static final Logger LOGGER = Logger.getLogger(CoberturaRowMapper.class);

	@Override
	public CoberturaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CoberturaVO cobertura = new CoberturaVO();
		cobertura.setPlanoContratado(rs.getString("PLANO"));
		cobertura.setId(rs.getInt("ID"));
		cobertura.setPremioCobertura(rs.getDouble("PREMIO_COBERTURA"));
		cobertura.setLmiCobertura(rs.getInt("LMI_COBERTURA"));
		cobertura.setLmiParcela(rs.getInt("LMI_PARCELA"));
		cobertura.setLmiPercentual(rs.getDouble("LMI_PERCENTUAL"));
		return cobertura;
	}
}
