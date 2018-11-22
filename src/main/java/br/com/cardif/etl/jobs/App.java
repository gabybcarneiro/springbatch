package br.com.cardif.etl.jobs;


import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;

import br.com.cardif.etl.annotations.Internal;
import br.com.cardif.etl.annotations.Optional;
import br.com.cardif.etl.annotations.Required;
import br.com.cardif.etl.annotations.Start;
import br.com.cardif.etl.util.CardifEtlUtils;

@br.com.cardif.etl.annotations.Job(springBatchContextConfiguration = "classpath*:/META-INF/spring/batch/config-context.xml")
public class App extends AbstractApp {

	private static final Logger LOGGER = Logger.getLogger(App.class);

	@Internal
	private ApplicationContext context;

	@Internal
	private Long jobId;

	@Internal
	private Long executionId;

	@Internal
	private Long userId;

	@Optional
	private String boneco = "";

//	@Optional
//	private Integer versao = 0;

	@Required
	private String layoutVersionId = "40";

	private Exception error = null;

	/**
	 * A anotação @Start indica ao ETL, que é a partir deste método que o quartz deve iniciar a execução do job.
	 * 
	 * @throws JobException
	 */
	@Start
	public void run() throws JobException {

		LOGGER.error("##################################################################");
		LOGGER.error("========== EXECUTANDO O JOB. ==========");

		BatchStatus jobStatus = null;
		JobExecution jobExecution = null;
		CardifEtlUtils cardifEtlUtils = null;
		Job job = null;

		try {
			MDC.put("executionId", executionId);
			MDC.put("jobId", jobId);
			MDC.put("userId", userId);

			JobParameters param = buildParameters();

			LOGGER.debug("Recuperando bean 'cardifEtlUtils' do contexto...");
			cardifEtlUtils = (CardifEtlUtils) context.getBean("cardifEtlUtils");

			if (cardifEtlUtils == null) {
				throw new JobException("Nao foi possivel recuperar o objeto 'cardifEtlUtils'. Verifique o arquivo de configuracoes do Spring.");
			}

			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			job = (Job) context.getBean("jobGraficaGarantia");

			LOGGER.warn("Iniciando execucao job " + job.getName());

			jobExecution = jobLauncher.run(job, param);
			jobStatus = jobExecution.getStatus();
		} catch (Exception e) {
			error = e;
		} finally {
			if (jobStatus != null && jobStatus.equals(BatchStatus.COMPLETED)) {
				cardifEtlUtils.done(executionId.intValue(), true);
				LOGGER.warn("Execucao do job '" + job.getName() + "' finalizada com sucesso.");
			} else {
				cardifEtlUtils.done(executionId.intValue(), false);
				throw JobUtils.JobExceptionFactory(job, JobUtils.getExecutionErrors(jobExecution), error);
			}
		}
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getExecutionId() {
		return executionId;
	}

	public void setExecutionId(Long executionId) {
		this.executionId = executionId;
	}

	public String getLayoutVersionId() {
		return layoutVersionId;
	}

	public void setLayoutVersionId(String layoutVersionId) {
		this.layoutVersionId = layoutVersionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBoneco() {
		return boneco;
	}

	public void setBoneco(String boneco) {
		this.boneco = boneco;
	}

//	public Integer getVersao() {
//		return versao;
//	}
//
//	public void setVersao(Integer versao) {
//		this.versao = versao;
//	}

	/**
	 * Utilizado apenas pela versao standalone
	 */
	@Override
	public String getDatabaseUrl() {
		return null;
	}

	/**
	 * Utilizado apenas pela versao standalone
	 */
	@Override
	public String getDatabaseUsername() {
		return null;
	}

	/**
	 * Utilizado apenas pela versao standalone
	 */
	@Override
	public String getDatabaseDriverClassName() {
		return null;
	}

	/**
	 * Utilizado apenas pela versao standalone
	 */
	@Override
	public String getCardifDatabasePassword() {
		return null;
	}
}
