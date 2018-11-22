package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.vo.BeneficiarioVO;

public class BeneficiarioRowMapper implements RowMapper<BeneficiarioVO> {

	@Override
	public BeneficiarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BeneficiarioVO beneficiario = new BeneficiarioVO();
		beneficiario.setId(rs.getInt("ID"));
		beneficiario.setNomeBeneficiario(rs.getString("BENEFICIARIO"));
		beneficiario.setPercentualBeneficiario(rs.getDouble("PERCENTUAL"));
		beneficiario.setDataNascimentoBenef(rs.getDate("NASCIMENTO_BENEFICIARIO"));
		beneficiario.setGrauParentesco(rs.getString("GRAU_PARENTESCO"));
		return beneficiario;
	}
}
