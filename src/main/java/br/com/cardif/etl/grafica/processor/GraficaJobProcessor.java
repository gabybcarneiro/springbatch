package br.com.cardif.etl.grafica.processor;

import br.com.cardif.etl.grafica.annotations.LayoutField;
import br.com.cardif.etl.grafica.dao.ExternalServiceParamDAO;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cardif.etl.grafica.boneco.Boneco;
import br.com.cardif.etl.grafica.boneco.ProcessaBoneco;
import br.com.cardif.etl.grafica.pojo.DadosSegurado;
import br.com.cardif.etl.grafica.pojo.ExtracaoGrafica;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;


public class GraficaJobProcessor implements ItemProcessor<DadosSegurado, Object> {

	private static final Logger LOGGER = Logger.getLogger(GraficaJobProcessor.class);

	@Autowired
	private ProcessaBoneco templateConfig;

	@Autowired
	ExternalServiceParamDAO esParamDAO;

	@Override
	public Map<String, Object> process(DadosSegurado item) throws Exception {
		LOGGER.error("GraficaJobProcessor --> Certificado: " + item.getCertificado());
		Boneco b = templateConfig.getTemplate(item.getTipoProduto());

		Map<String, Object> extracao = transformaDados(b.processaBoneco(item));
		extracao.putAll(esParamDAO.buscaValoresPadrao(item.getBoneco()));
		return extracao;
	}

	private Map<String, Object> transformaDados(ExtracaoGrafica items) throws IllegalAccessException {
		Map<String, Object> map = new LinkedHashMap<>();

		Field[] fields = items.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if(field.get(items) != null) {
				Field[] attributeFields = field.get(items).getClass().getDeclaredFields();
				for (Field attributeField : attributeFields) {
					attributeField.setAccessible(true);
					// Procura pela annotation que mapeia as posições do arquivo que equivalem a cada campo do POJO
					List<Annotation> listAnnotations = Arrays.asList(attributeField.getAnnotations());
					for (Annotation annotation : listAnnotations) {
						if (annotation instanceof LayoutField) {
							Object obj = attributeField.get(field.get(items));
							String[] nameField = ((LayoutField) annotation).nameField();

							for(int index = 0; index < nameField.length ; index++) {
								if(obj != null) {
									map.put(nameField[index].toString(), obj);
								}
							}
						}
					}
				}
			} else {
				continue;
			}
		}
		return map;
	}

}
