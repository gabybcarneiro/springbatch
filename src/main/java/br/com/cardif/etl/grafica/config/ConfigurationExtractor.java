package br.com.cardif.etl.grafica.config;

		import br.com.cardif.etl.grafica.dao.ExternalServiceDAO;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.stereotype.Component;

		import java.util.List;

@Component
public class ConfigurationExtractor {

	@Autowired
	private ExternalServiceDAO dao;

	public List<String> getBonecosForExtract(){
		return dao.getBonecosForExtract();
	}
}
