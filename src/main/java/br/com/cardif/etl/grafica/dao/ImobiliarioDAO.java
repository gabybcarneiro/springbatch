package br.com.cardif.etl.grafica.dao;

import java.util.Map;

public interface ImobiliarioDAO {

	public Map<String, Object> buscaCapitalizacao(Integer certificado);
	
}
