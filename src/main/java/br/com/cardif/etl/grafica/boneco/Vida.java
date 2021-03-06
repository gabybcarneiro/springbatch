package br.com.cardif.etl.grafica.boneco;

import br.com.cardif.etl.grafica.pojo.DadosSegurado;
import br.com.cardif.etl.grafica.pojo.ExtracaoGrafica;

public class Vida extends ExtracaoGraficaNegocio implements Boneco {

    @Override
    public ExtracaoGrafica processaBoneco(DadosSegurado dadosCertificado){

        ExtracaoGrafica extracaoGrafica = processaCertificado(dadosCertificado);
        extracaoGrafica.setCapitalizacao(processaCapitalizacao(dadosCertificado.getCertificado()));
        extracaoGrafica.setCobertura(processaCoberturas(dadosCertificado.getContrato()));
        extracaoGrafica.setBeneficiario(processaBeneficiarios(dadosCertificado.getCertificado(), dadosCertificado.getEndosso()));
        extracaoGrafica.setEstipulante(processaEstipulante(dadosCertificado.getCertificado(), dadosCertificado.getEndosso())); ;
        extracaoGrafica.setSubestipulantes(processaSubEstipulantes(dadosCertificado.getCertificado(), dadosCertificado.getEndosso()));
        extracaoGrafica.setOtherInfo(processaOtherInfo());

        return extracaoGrafica;
    }
}
