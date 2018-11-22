package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cardif.etl.grafica.util.TipoCombustivel;
import br.com.cardif.etl.grafica.util.TipoSimNao;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.pojo.Veiculo;

public class VeiculoRowMapper implements RowMapper<Veiculo> {

	private static final Logger LOGGER = Logger.getLogger(VeiculoRowMapper.class);

	@Override
	public Veiculo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Veiculo veiculo = new Veiculo();
		veiculo.setMarca(rs.getString("FIPE_BRAND_CAR"));
		veiculo.setModelo(rs.getString("FIPE_MODEL_CAR"));
		veiculo.setCodReferencia(rs.getInt("FIPE_COD"));
		veiculo.setAnoModelo(rs.getString("COI_CAR_MODEL_YEAR"));
		veiculo.setPlaca(rs.getString("COI_CAR_LICENSE_PLATE"));
		veiculo.setNumeroChassi(rs.getString("COI_CAR_CHASSI_NUMEBR"));
		veiculo.setRenavam(rs.getString("COI_CAR_RENAVAM_NUMBER"));
		veiculo.setCombustivel(TipoCombustivel.getDescByValue(rs.getString("COI_CAR_FUEL")));
		veiculo.setZero(TipoSimNao.getDescByValue(rs.getString("COI_CAR_FIPE_ZEROKM")));
		veiculo.setCor(rs.getString("COLOR_DSC"));
		veiculo.setCirculacao(rs.getString("CIRCULACAO"));
		return veiculo;
	}
}
