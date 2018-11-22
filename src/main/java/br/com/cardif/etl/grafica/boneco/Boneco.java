package br.com.cardif.etl.grafica.boneco;


import br.com.cardif.etl.grafica.pojo.DadosSegurado;
import br.com.cardif.etl.grafica.pojo.ExtracaoGrafica;

import java.util.Map;

public interface Boneco {
	
	public ExtracaoGrafica processaBoneco(DadosSegurado certificado);
}
