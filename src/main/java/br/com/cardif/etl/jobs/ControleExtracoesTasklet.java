package br.com.cardif.etl.jobs;

import br.com.cardif.etl.grafica.util.GraficaUtil;
import br.com.cardif.etl.jobs.spring.EtlLayoutLineAggregator;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cardif.etl.grafica.dao.CertificadoDAO;
import br.com.cardif.etl.grafica.dao.ExternalServiceDAO;

import java.util.Date;

@Component
public class ControleExtracoesTasklet implements Tasklet, InitializingBean {

	private static final Logger LOGGER = Logger.getLogger(ControleExtracoesTasklet.class);

	@Autowired
	ExternalServiceDAO externalServiceDAO;
	
	@Autowired
	CertificadoDAO certificadoDAO;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		String boneco = chunkContext.getStepContext().getJobParameters().get("boneco").toString();
		certificadoDAO.atualizaCertificadosExtraidos(boneco);
		externalServiceDAO.atualizaExternalService(boneco);
		return RepeatStatus.FINISHED;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}
}
