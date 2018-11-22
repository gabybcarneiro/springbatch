package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.pojo.Parcela;

public class ParcelaRowMapper implements RowMapper<Parcela> {

	@Override
	public Parcela mapRow(ResultSet rs, int rowNum) throws SQLException {
		Parcela parcela = new Parcela();
		parcela.setValorParcela(rs.getDouble("VALOR_PARCELA"));
		parcela.setValorTotal(rs.getDouble("VALOR_TOTAL"));
		parcela.setIof(rs.getDouble("IOF"));

		parcela.setParcelaInicial(rs.getInt("PARCELA_INICIAL"));
		parcela.setTotalParcela(rs.getInt("TOTAL_PARCELAS"));

		return parcela;
	}
}
