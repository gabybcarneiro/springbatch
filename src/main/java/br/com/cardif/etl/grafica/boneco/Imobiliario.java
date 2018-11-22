package br.com.cardif.etl.grafica.boneco;

import br.com.cardif.etl.grafica.pojo.DadosSegurado;
import br.com.cardif.etl.grafica.pojo.ExtracaoGrafica;

public class Imobiliario extends ExtracaoGraficaNegocio implements Boneco {

    @Override
    public ExtracaoGrafica processaBoneco(DadosSegurado dadosCertificado){
        return super.processaBoneco(dadosCertificado);
    }
}
