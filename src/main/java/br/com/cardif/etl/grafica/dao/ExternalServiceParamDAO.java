package br.com.cardif.etl.grafica.dao;

import br.com.cardif.etl.grafica.pojo.ExternalService;

import java.util.List;
import java.util.Map;

public interface ExternalServiceParamDAO {

	public Map<String, Object> buscaValoresPadrao(String boneco);

}
