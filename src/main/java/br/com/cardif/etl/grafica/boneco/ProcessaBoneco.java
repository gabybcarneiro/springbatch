package br.com.cardif.etl.grafica.boneco;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

@Component
public class ProcessaBoneco {

	private Map<Integer, ? extends Boneco> templateMapping;

	public void setTemplateMapping(Map<Integer, ? extends Boneco> templateMapping) {
		this.templateMapping = templateMapping;
	}

	public Map<Integer, ? extends Boneco> getTemplateMapping() {
		return templateMapping;
	}

	public Boneco getTemplate(Integer tipo) {
		if (MapUtils.isNotEmpty(this.templateMapping)) {
			return this.templateMapping.get(tipo);
		}
		return null;
	}
}
