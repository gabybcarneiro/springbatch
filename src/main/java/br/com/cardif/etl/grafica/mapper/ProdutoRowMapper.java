package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.pojo.Produto;

public class ProdutoRowMapper implements RowMapper<Produto> {

	private static final Logger LOGGER = Logger.getLogger(ProdutoRowMapper.class);

	@Override
	public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
		Produto produto = new Produto();
		produto.setCodigoProduto(rs.getInt("CODIGO_PRODUTO"));
		produto.setMarca(rs.getString("MARCA_PRODUTO"));
		produto.setModelo(rs.getString("MODELO_PRODUTO"));
		produto.setDescricao(rs.getString("DESCRICAO_PRODUTO"));
		produto.setNumeroImei(rs.getInt("IMEI"));
		produto.setInicioGarantia(rs.getDate("INICIO_GARANTIA_FABRICA"));
		produto.setFimGarantia(rs.getDate("FIM_GARANTIA_FABRICA"));
		produto.setVigenciaFabricante(rs.getDate("VIGENCIA_GARANTIA_FABRICANTE"));
		
		return produto;
	}
}
