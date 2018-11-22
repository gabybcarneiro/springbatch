package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import br.com.cardif.etl.grafica.vo.SubestipulanteVO;

public class SubestipulanteRowMapper implements RowMapper<SubestipulanteVO> {

	@Override
	public SubestipulanteVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubestipulanteVO subEstipulante = new SubestipulanteVO();
		subEstipulante.setId(rs.getInt("ID"));
		subEstipulante.setNomeSubestipulante(rs.getString("SUBESTIPULANTE"));
		subEstipulante.setCnpjSubestipulante(rs.getString("SUBESTIPULANTE_CNPJ"));
		subEstipulante.setPercProlabore(rs.getDouble("PERCENTUAL_PROLABORE_SUBESTIPULANTE"));
		subEstipulante.setValorProlabore(rs.getDouble("VALOR"));
		subEstipulante.setEnderecoSubestipulante(rs.getString("ENDERECO"));
		subEstipulante.setComplementoSubestipulante(rs.getString("COMPLEMENTO"));
		subEstipulante.setBairroSubestipulante(rs.getString("BAIRRO"));
		subEstipulante.setCidadeSubestipulante(rs.getString("CIDADE"));
		subEstipulante.setEstadoSubestipulante(rs.getString("ESTADO"));
		subEstipulante.setCepSubestipulante(rs.getString("CEP"));
		subEstipulante.setDddSubestipulante(rs.getString("DDD"));
		subEstipulante.setTelefoneSubestipulante(rs.getString("TELEFONE"));
		return subEstipulante;
	}
}
