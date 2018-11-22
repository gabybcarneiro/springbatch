package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.vo.CorretorVO;

public class CorretorRowMapper implements RowMapper<CorretorVO> {

	private static final Logger LOGGER = Logger.getLogger(CorretorRowMapper.class);

	@Override
	public CorretorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CorretorVO corretorVO = new CorretorVO();
		corretorVO.setNomeCorretor(rs.getString("CORRETOR_NAME"));
		corretorVO.setCnpjCorretor(rs.getInt("CORRETOR_CNPJ"));
		corretorVO.setRegistroCorretor(rs.getString("CORRETOR_REGISTRO_SUSEP"));
		corretorVO.setEnderecoCorretor(rs.getString("CORRETOR_LOGRADOURO"));
		corretorVO.setComplementoCorretor(rs.getString("CORRETOR_COMPLEMENTO"));
		corretorVO.setBairroCorretor(rs.getString("CORRETOR_BAIRRO"));
		corretorVO.setCidadeCorretor(rs.getString("CORRETOR_CIDADE"));
		corretorVO.setEstadoCorretor(rs.getString("CORRETOR_ESTADO"));
		corretorVO.setCepCorretor(rs.getInt("CORRETOR_CEP"));
		corretorVO.setDddCorretor(rs.getInt("CORRETOR_DDD"));
		corretorVO.setTelefoneCorretor(rs.getInt("CORRETOR_TELEFONE"));
		corretorVO.setEmailCorretor(rs.getString("EMAIL_CORRETOR"));
		
		return corretorVO;
	}
}
