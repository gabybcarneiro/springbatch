package br.com.cardif.etl.grafica.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.cardif.etl.grafica.pojo.*;
import br.com.cardif.etl.grafica.vo.CoberturaVO;

public interface CertificadoDAO {

	public List<DadosSegurado> buscaDadosSegurado(String boneco);

	public Parcela buscaParcela(Integer certificado, Integer endossoCertif);
	
	public Capitalizacao buscaCapitalizacao(Integer certificado);
	
	public Double buscaFactorParaCalculo(Integer certificado, Integer endosso);
	
	public List<CoberturaVO> buscaCoberturas(String contrato);
	
	public Veiculo buscaVeiculo(Integer certificado);
	
	public Produto buscaProduto(Integer certificado, Integer endosso);

	public void atualizaCertificadosExtraidos(String boneco);
}
