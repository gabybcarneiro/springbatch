package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.pojo.Capitalizacao;

public class CapitalizacaoRowMapper implements RowMapper<Capitalizacao> {

	@Override
	public Capitalizacao mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Capitalizacao cap = new Capitalizacao();
		cap.setSerie(rs.getInt("SERIE"));
		cap.setNumeroSorte(rs.getInt("NUMERO_SORTE"));
		cap.setCapVigencia(rs.getString("CAP_VIGENCIA"));
		cap.setCapPeriodicidade(rs.getString("CAP_PERIODICIDADE"));
		cap.setCapPremioLiquido(rs.getDouble("CAP_PREMIO_LIQUIDO"));
		cap.setCapPremioBruto(rs.getDouble("CAP_PREMIO_BRUTO"));
		cap.setCapIr(rs.getDouble("CAP_IR"));
		
		return cap;
	}
}
