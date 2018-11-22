package br.com.cardif.etl.grafica.dao;

import java.util.Map;

import br.com.cardif.etl.grafica.pojo.Estipulante;
import br.com.cardif.etl.grafica.vo.BeneficiarioVO;
import br.com.cardif.etl.grafica.vo.CorretorVO;
import br.com.cardif.etl.grafica.vo.SubestipulanteVO;

public interface ParticipantesDAO {

	public Map<Integer, BeneficiarioVO> buscaBeneficiarios(Integer certificado, Integer endosso);
	public Estipulante buscaEstipulante(Integer certificado, Integer endosso);
	public Map<Integer, SubestipulanteVO> buscaSubestipulante(Integer certificado, Integer endosso);
	public Map<Integer, CorretorVO> buscaCorretores(Integer certificado, Integer endosso);
}
