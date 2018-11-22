package br.com.cardif.etl.grafica.dao;

import br.com.cardif.etl.grafica.pojo.ExternalService;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface ExternalServiceDAO {

	public Integer getSequence(String boneco);
	
	public String getFileName(String boneco);
	
	public ExternalService getFileNameSequence(String boneco);
	
	public ExternalService getExternalService(String boneco);
	
	public void atualizaExternalService(String boneco);

	public Date getDtUltimaExtracao(String boneco);

	public List<String> getBonecosForExtract();
}
