package br.com.cardif.etl.grafica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.cardif.etl.grafica.pojo.DadosSegurado;


public class DadosSeguradoRowMapper implements RowMapper<DadosSegurado> {

	@Override
	public DadosSegurado mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DadosSegurado dadosSegurado = new DadosSegurado();
		dadosSegurado.setBoneco(rs.getString("BONECO"));
		dadosSegurado.setTipoProduto(rs.getInt("TIPOPRODUTO")); 
		dadosSegurado.setNomeSegurado(rs.getString("NOME_SEGURADO"));
		dadosSegurado.setCpfCnpj(rs.getLong("CPF_CNPJ"));
		dadosSegurado.setApolice(rs.getInt("APOLICE"));
		dadosSegurado.setEndossoApolice(rs.getInt("ENDOSSO_APOLICE"));
		dadosSegurado.setApolice(rs.getInt("APOLICE_VARIAVEL"));
		dadosSegurado.setDtNascimento(rs.getDate("DTNASCIMENTO"));
		dadosSegurado.setSexo(rs.getString("SEXO"));
		dadosSegurado.setLogradouro(rs.getString("LOGRADOURO"));
		dadosSegurado.setComplemento(rs.getString("COMPLEMENTO"));
		dadosSegurado.setBairro(rs.getString("BAIRRO"));
		dadosSegurado.setCidade(rs.getString("CIDADE"));
		dadosSegurado.setEstado(rs.getString("ESTADO"));
		dadosSegurado.setCep(rs.getInt("CEP"));
		dadosSegurado.setEmail(rs.getString("EMAIL"));
		dadosSegurado.setEmailComplementar(rs.getString("EMAIL_COMPLEMENTAR"));
		dadosSegurado.setNacionalidade(rs.getString("NACIONALIDADE"));
		dadosSegurado.setContrato(rs.getString("CONTRATO"));
		dadosSegurado.setCertificado(rs.getInt("CERTIFICADO"));
		dadosSegurado.setEndosso(rs.getInt("ENDOSSO"));
		dadosSegurado.setDtAdesao(rs.getDate("DATA_ADESAO"));
		dadosSegurado.setDtInicioVigencia(rs.getDate("INICIO_VIGENCIA"));
		dadosSegurado.setDtFimVigencia(rs.getDate("FIM_VIGENCIA"));
		dadosSegurado.setValorFinanciado(rs.getDouble("VALOR_FINANCIADO"));
		dadosSegurado.setNumeroCartaoSegurado(rs.getString("NUMERO_CARTAO_SEGURADO"));
		dadosSegurado.setIdParceiro(rs.getInt("ID_PARCEIRO"));
		dadosSegurado.setCodigoProduto(rs.getString("POLCRT_PARTNER_PRODUCT_DS"));
		dadosSegurado.setEnderecoRisco(rs.getString("ENDERECO_RISCO"));
		dadosSegurado.setComplementoRisco(rs.getString("COMPLEMENTO_RISCO"));
		dadosSegurado.setBairroRisco(rs.getString("BAIRRO_RISCO"));
		dadosSegurado.setCidadeRisco(rs.getString("CIDADE_RISCO"));
		dadosSegurado.setEstadoRisco(rs.getString("ESTADO_RISCO"));
		dadosSegurado.setCepRisco(rs.getInt("CEP_RISCO"));

		return dadosSegurado;
	}
}
