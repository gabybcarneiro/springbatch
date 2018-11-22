package br.com.cardif.etl.grafica.boneco;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.cardif.etl.grafica.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cardif.etl.grafica.dao.CertificadoDAO;
import br.com.cardif.etl.grafica.dao.ParticipantesDAO;
import br.com.cardif.etl.grafica.vo.BeneficiarioVO;
import br.com.cardif.etl.grafica.vo.CoberturaVO;
import br.com.cardif.etl.grafica.vo.CorretorVO;
import br.com.cardif.etl.grafica.vo.SubestipulanteVO;

@Service
public class ExtracaoGraficaNegocio {

	@Autowired
	CertificadoDAO certificadoDAO;

	@Autowired
	ParticipantesDAO participantesDAO;

	public ExtracaoGrafica processaBoneco(DadosSegurado dadosCertificado) {

		ExtracaoGrafica extracaoGrafica = processaCertificado(dadosCertificado);
		extracaoGrafica.setCapitalizacao(processaCapitalizacao(dadosCertificado.getCertificado()));
		extracaoGrafica.setCobertura(processaCoberturas(dadosCertificado.getContrato()));
		extracaoGrafica.setBeneficiario(processaBeneficiarios(dadosCertificado.getCertificado(), dadosCertificado.getEndosso()));
		extracaoGrafica.setVeiculo(processaVeiculo(dadosCertificado.getCertificado()));
		extracaoGrafica.setProduto(processaProduto(dadosCertificado.getCertificado(), dadosCertificado.getEndosso()));
		extracaoGrafica.setCorretor(processaCorretores(dadosCertificado.getCertificado(), dadosCertificado.getEndosso()));
		extracaoGrafica.setEstipulante(processaEstipulante(dadosCertificado.getCertificado(), dadosCertificado.getEndosso())); ;
		extracaoGrafica.setSubestipulantes(processaSubEstipulantes(dadosCertificado.getCertificado(), dadosCertificado.getEndosso()));
		extracaoGrafica.setOtherInfo(processaOtherInfo());
//		 processaInfoImobiliario();

		return extracaoGrafica;
	}

	public ExtracaoGrafica processaCertificado(DadosSegurado dadosCertificado) {
		//HardCode
		dadosCertificado.setImpressao(1);

		ExtracaoGrafica eg = new ExtracaoGrafica();
		eg.setDadosSegurado(dadosCertificado);

		Parcela parcela = certificadoDAO.buscaParcela( dadosCertificado.getCertificado(), dadosCertificado.getEndosso());
		if (parcela.getValorTotal() == parcela.getValorParcela()) {
			parcela.setPeriodicidade("UNICO");
		} else {
			parcela.setPeriodicidade("MENSAL");
		}
		eg.setParcela(parcela);

		return eg;
	}

	public Capitalizacao processaCapitalizacao(Integer certificado) {
		return certificadoDAO.buscaCapitalizacao(certificado);
	}

	public Cobertura processaCoberturas(String contrato) {
		Cobertura cobertura = new Cobertura();
		CoberturaVO c = null;
		Map<Integer, CoberturaVO> listaCoberturas = agrupaCoberturas(certificadoDAO.buscaCoberturas(contrato));

		if(listaCoberturas != null) {
			if (listaCoberturas.get(1) != null) {
				c = listaCoberturas.get(1);
				cobertura.setPlanoContratado(c.getPlanoContratado());
				cobertura.setPremioCobertura1(c.getPremioCobertura());
				cobertura.setLmiParcela1(c.getLmiParcela());
				cobertura.setLmiPercentual1(c.getLmiPercentual());
				if(c.getLmiParcela() > 1){
					cobertura.setLmiCobertura1(c.getLmiCobertura() / c.getLmiParcela());
				} else {
					cobertura.setLmiCobertura1(c.getLmiCobertura());
				}
			}

			if (listaCoberturas.get(2) != null) {
				c = listaCoberturas.get(2);
				cobertura.setPremioCobertura2(c.getPremioCobertura());
				cobertura.setLmiParcela2(c.getLmiParcela());
				cobertura.setLmiPercentual2(c.getLmiPercentual());
				if(c.getLmiParcela() > 1){
					cobertura.setLmiCobertura2(c.getLmiCobertura() / c.getLmiParcela());
				} else {
					cobertura.setLmiCobertura2(c.getLmiCobertura());
				}
			}

			if (listaCoberturas.get(3) != null) {
				c = listaCoberturas.get(3);
				cobertura.setPremioCobertura3(c.getPremioCobertura());
				cobertura.setLmiParcela3(c.getLmiParcela());
				if(c.getLmiParcela() > 1){
					cobertura.setLmiCobertura3(c.getLmiCobertura() / c.getLmiParcela());
				} else {
					cobertura.setLmiCobertura3(c.getLmiCobertura());
				}
			}

			if (listaCoberturas.get(4) != null) {
				c = listaCoberturas.get(4);
				cobertura.setPremioCobertura4(c.getPremioCobertura());
				cobertura.setLmiParcela4(c.getLmiParcela());
				if(c.getLmiParcela() > 1){
					cobertura.setLmiCobertura4(c.getLmiCobertura() / c.getLmiParcela());
				} else {
					cobertura.setLmiCobertura4(c.getLmiCobertura());
				}
			}

			if (listaCoberturas.get(5) != null) {
				c = listaCoberturas.get(5);
				cobertura.setPremioCobertura5(c.getPremioCobertura());
				cobertura.setLmiParcela5(c.getLmiParcela());
				if(c.getLmiParcela() > 1){
					cobertura.setLmiCobertura5(c.getLmiCobertura() / c.getLmiParcela());
				} else {
					cobertura.setLmiCobertura5(c.getLmiCobertura());
				}
			}

			if (listaCoberturas.get(6) != null) {
				c = listaCoberturas.get(6);
				cobertura.setPremioCobertura6(c.getPremioCobertura());
				cobertura.setLmiCobertura6(c.getLmiCobertura());
			}

			if (listaCoberturas.get(7) != null) {
				c = listaCoberturas.get(7);
				cobertura.setPremioCobertura7(c.getPremioCobertura());
				cobertura.setLmiCobertura7(c.getLmiCobertura());
			}

			if (listaCoberturas.get(8) != null) {
				c = listaCoberturas.get(8);
				cobertura.setPremioCobertura8(c.getPremioCobertura());
				cobertura.setLmiCobertura8(c.getLmiCobertura());
			}

			if (listaCoberturas.get(9) != null) {
				c = listaCoberturas.get(9);
				cobertura.setPremioCobertura9(c.getPremioCobertura());
				cobertura.setLmiCobertura9(c.getLmiCobertura());
			}

			if (listaCoberturas.get(10) != null) {
				c = listaCoberturas.get(10);
				cobertura.setPremioCobertura10(c.getPremioCobertura());
				cobertura.setLmiCobertura10(c.getLmiCobertura());
			}
		}
		return cobertura;
	}

	private Map<Integer, CoberturaVO> agrupaCoberturas(List<CoberturaVO> list){
		Map<Integer, CoberturaVO> map = new TreeMap<Integer, CoberturaVO>();
		for(CoberturaVO c : list){
			map.put(c.getId(), c);
		}
 		return map;
	}

	public Beneficiario processaBeneficiarios(Integer certificado, Integer endosso) {
		Beneficiario beneficiario = new Beneficiario();
		Map<Integer, BeneficiarioVO> list = participantesDAO.buscaBeneficiarios(certificado, endosso);

		if (list.get(1) != null) {
			beneficiario.setNomeBeneficiario1(list.get(1).getNomeBeneficiario());
			beneficiario.setPercentualBeneficiario1(list.get(1).getPercentualBeneficiario());
			beneficiario.setDataNascimentoBenef1(list.get(1).getDataNascimentoBenef());
			beneficiario.setGrauParentesco1(list.get(1).getGrauParentesco());
		}

		if (list.get(2) != null) {
			beneficiario.setNomeBeneficiario2(list.get(2).getNomeBeneficiario());
			beneficiario.setPercentualBeneficiario2(list.get(2).getPercentualBeneficiario());
			beneficiario.setDataNascimentoBenef2(list.get(2).getDataNascimentoBenef());
			beneficiario.setGrauParentesco2(list.get(2).getGrauParentesco());
		}

		if (list.get(3) != null) {
			beneficiario.setNomeBeneficiario3(list.get(3).getNomeBeneficiario());
			beneficiario.setPercentualBeneficiario3(list.get(3).getPercentualBeneficiario());
			beneficiario.setDataNascimentoBenef3(list.get(3).getDataNascimentoBenef());
			beneficiario.setGrauParentesco3(list.get(3).getGrauParentesco());
		}

		if (list.get(4) != null) {
			beneficiario.setNomeBeneficiario4(list.get(4).getNomeBeneficiario());
			beneficiario.setPercentualBeneficiario4(list.get(4).getPercentualBeneficiario());
			beneficiario.setDataNascimentoBenef4(list.get(4).getDataNascimentoBenef());
			beneficiario.setGrauParentesco4(list.get(4).getGrauParentesco());
		}

		if (list.get(5) != null) {
			beneficiario.setNomeBeneficiario5(list.get(5).getNomeBeneficiario());
			beneficiario.setPercentualBeneficiario5(list.get(5).getPercentualBeneficiario());
			beneficiario.setDataNascimentoBenef4(list.get(5).getDataNascimentoBenef());
			beneficiario.setGrauParentesco5(list.get(5).getGrauParentesco());
		}
		return beneficiario;
	}

	public Estipulante processaEstipulante(Integer certificado, Integer endosso) {
		return participantesDAO.buscaEstipulante(certificado, endosso);
	}

	public Subestipulante processaSubEstipulantes(Integer certificado, Integer endosso) {
		Subestipulante subestipulante = new Subestipulante();
		Map<Integer, SubestipulanteVO> listaSubestipulante = participantesDAO.buscaSubestipulante(certificado, endosso);

		if (listaSubestipulante.get(1) != null) {
			subestipulante.setNomeSubestipulante1(listaSubestipulante.get(1).getNomeSubestipulante());
			subestipulante.setCnpjSubestipulante1(listaSubestipulante.get(1).getCnpjSubestipulante());
			subestipulante.setPercProlabore1(listaSubestipulante.get(1).getPercProlabore());
			subestipulante.setValorProlabore1(listaSubestipulante.get(1).getValorProlabore());
			subestipulante.setEnderecoSubestipulante1(listaSubestipulante.get(1).getEnderecoSubestipulante());
			subestipulante.setComplementoSubestipulante1(listaSubestipulante.get(1).getComplementoSubestipulante());
			subestipulante.setBairroSubestipulante1(listaSubestipulante.get(1).getBairroSubestipulante());
			subestipulante.setCidadeSubestipulante1(listaSubestipulante.get(1).getCidadeSubestipulante());
			subestipulante.setEstadoSubestipulante1(listaSubestipulante.get(1).getEstadoSubestipulante());
			subestipulante.setCepSubestipulante1(listaSubestipulante.get(1).getCepSubestipulante());
			subestipulante.setDddSubestipulante1(listaSubestipulante.get(1).getDddSubestipulante());
			subestipulante.setTelefoneSubestipulante1(listaSubestipulante.get(1).getTelefoneSubestipulante());
		}

		if (listaSubestipulante.get(2) != null) {
			subestipulante.setNomeSubestipulante2(listaSubestipulante.get(2).getNomeSubestipulante());
			subestipulante.setCnpjSubestipulante2(listaSubestipulante.get(2).getCnpjSubestipulante());
			subestipulante.setPercProlabore2(listaSubestipulante.get(2).getPercProlabore());
			subestipulante.setValorProlabore2(listaSubestipulante.get(2).getValorProlabore());
			subestipulante.setEnderecoSubestipulante2(listaSubestipulante.get(2).getEnderecoSubestipulante());
			subestipulante.setComplementoSubestipulante2(listaSubestipulante.get(2).getComplementoSubestipulante());
			subestipulante.setBairroSubestipulante2(listaSubestipulante.get(2).getBairroSubestipulante());
			subestipulante.setCidadeSubestipulante2(listaSubestipulante.get(2).getCidadeSubestipulante());
			subestipulante.setEstadoSubestipulante2(listaSubestipulante.get(2).getEstadoSubestipulante());
			subestipulante.setCepSubestipulante2(listaSubestipulante.get(2).getCepSubestipulante());
			subestipulante.setDddSubestipulante2(listaSubestipulante.get(2).getDddSubestipulante());
			subestipulante.setTelefoneSubestipulante2(listaSubestipulante.get(2).getTelefoneSubestipulante());
		}

		return subestipulante;
	}

	public Corretor processaCorretores(Integer certificado, Integer endosso) {
		Corretor corretor = new Corretor();
		Map<Integer, CorretorVO> listCorretores = participantesDAO.buscaCorretores(certificado, endosso);

		if (listCorretores.get(1) != null) {
			corretor.setNomeCorretor(listCorretores.get(1).getNomeCorretor());
			corretor.setCnpjCorretor(listCorretores.get(1).getCnpjCorretor());
			corretor.setRegistroCorretor(listCorretores.get(1).getRegistroCorretor());
			corretor.setEnderecoCorretor(listCorretores.get(1).getEnderecoCorretor());
			corretor.setComplementoCorretor(listCorretores.get(1).getComplementoCorretor());
			corretor.setBairroCorretor(listCorretores.get(1).getBairroCorretor());
			corretor.setCidadeCorretor(listCorretores.get(1).getCidadeCorretor());
			corretor.setEstadoCorretor(listCorretores.get(1).getEstadoCorretor());
			corretor.setCepCorretor(listCorretores.get(1).getCepCorretor());
			corretor.setDddCorretor(listCorretores.get(1).getDddCorretor());
			corretor.setTelefoneCorretor(listCorretores.get(1).getTelefoneCorretor());
			corretor.setEmailCorretor(listCorretores.get(1).getEmailCorretor());
		}

		if (listCorretores.get(2) != null) {
			corretor.setNomeCoCorretor1(listCorretores.get(2).getNomeCorretor());
			corretor.setCnpjCoCorretor1(listCorretores.get(2).getCnpjCorretor());
			corretor.setRegistroCoCorretor1(listCorretores.get(2).getRegistroCorretor());
			corretor.setEnderecoCoCorretor1(listCorretores.get(2).getEnderecoCorretor());
			corretor.setComplementoCoCorretor1(listCorretores.get(2).getComplementoCorretor());
			corretor.setBairroCoCorretor1(listCorretores.get(2).getBairroCorretor());
			corretor.setCidadeCoCorretor1(listCorretores.get(2).getCidadeCorretor());
			corretor.setEstadoCoCorretor1(listCorretores.get(2).getEstadoCorretor());
			corretor.setCepCoCorretor1(listCorretores.get(2).getCepCorretor());
			corretor.setDddCoCorretor1(listCorretores.get(2).getDddCorretor());
			corretor.setTelefoneCoCorretor1(listCorretores.get(2).getTelefoneCorretor());
			corretor.setEmailCoCorretor1(listCorretores.get(2).getEmailCorretor());
		}

		if (listCorretores.get(3) != null) {
			corretor.setNomeCoCorretor2(listCorretores.get(3).getNomeCorretor());
			corretor.setCnpjCoCorretor2(listCorretores.get(3).getCnpjCorretor());
			corretor.setRegistroCoCorretor2(listCorretores.get(3).getRegistroCorretor());
			corretor.setEnderecoCoCorretor2(listCorretores.get(3).getEnderecoCorretor());
			corretor.setComplementoCoCorretor2(listCorretores.get(3).getComplementoCorretor());
			corretor.setBairroCoCorretor2(listCorretores.get(3).getBairroCorretor());
			corretor.setCidadeCoCorretor2(listCorretores.get(3).getCidadeCorretor());
			corretor.setEstadoCoCorretor2(listCorretores.get(3).getEstadoCorretor());
			corretor.setCepCoCorretor2(listCorretores.get(3).getCepCorretor());
			corretor.setDddCoCorretor2(listCorretores.get(3).getDddCorretor());
			corretor.setTelefoneCoCorretor2(listCorretores.get(3).getTelefoneCorretor());
			corretor.setEmailCoCorretor2(listCorretores.get(3).getEmailCorretor());
		}

		if (listCorretores.get(4) != null) {
			corretor.setNomeCoCorretor3(listCorretores.get(4).getNomeCorretor());
			corretor.setCnpjCoCorretor3(listCorretores.get(4).getCnpjCorretor());
			corretor.setRegistroCoCorretor3(listCorretores.get(4).getRegistroCorretor());
			corretor.setEnderecoCoCorretor3(listCorretores.get(4).getEnderecoCorretor());
			corretor.setComplementoCoCorretor3(listCorretores.get(4).getComplementoCorretor());
			corretor.setBairroCoCorretor3(listCorretores.get(4).getBairroCorretor());
			corretor.setCidadeCoCorretor3(listCorretores.get(4).getCidadeCorretor());
			corretor.setEstadoCoCorretor3(listCorretores.get(4).getEstadoCorretor());
			corretor.setCepCoCorretor3(listCorretores.get(4).getCepCorretor());
			corretor.setDddCoCorretor3(listCorretores.get(4).getDddCorretor());
			corretor.setTelefoneCoCorretor3(listCorretores.get(4).getTelefoneCorretor());
			corretor.setEmailCoCorretor3(listCorretores.get(4).getEmailCorretor());
		}

		return corretor;
	}

	public Veiculo processaVeiculo(Integer certificado) {
		return certificadoDAO.buscaVeiculo(certificado);
	}

	public Produto processaProduto(Integer certificado, Integer endosso) {
		return certificadoDAO.buscaProduto(certificado, endosso);
	}

	public OtherInfo processaOtherInfo() {
		OtherInfo otherInfo = new OtherInfo();
		otherInfo.setPlanoAssitencia("Plano 1");
		return otherInfo;
	}
}
