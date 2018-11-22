package br.com.cardif.etl.jobs;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.cardif.etl.annotations.Internal;
import br.com.cardif.etl.annotations.Optional;
import br.com.cardif.etl.annotations.ReplaceParam;
import br.com.cardif.etl.annotations.Required;
import br.com.cardif.etl.jobs.AbstractApp;
import br.com.cardif.etl.util.CardifEtlUtils;
import br.com.cardif.etl.util.SQLUtils;
import javassist.bytecode.stackmap.TypeData.ClassName;

@br.com.cardif.etl.annotations.Job(springBatchContextConfiguration = "classpath*:/META-INF/spring/batch/config-context-teste.xml")
public class AppStandalone extends AbstractApp {

	private static final Logger LOGGER = Logger.getLogger(AppStandalone.class);

	private static Properties internalProperties;
	private static Properties externalProperties;

	@Internal
	private ApplicationContext context;

	@Internal
	private Long jobId;

	@Internal
	private Long executionId;

	@Internal
	private Long userId;

	@Required
	private String boneco = "RE12";

	@Required
	private String layoutVersionId = "1";

	@Required
	private String databaseDriverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	@Required
	private String databaseUrl = "jdbc:sqlserver://SAOS005DB09D;databaseName=dbETL;portNumber=1433"; 

	@Required
	private String databaseUsername = "userdes";

	@Required
	private String databasePassword = "userdes";

	@Required
	private String cardifDatabaseUrl = "jdbc:sqlserver://SAOS005DB09D;databaseName=dbProdLife;portNumber=1433";

	@Required
	private String cardifDatabaseUsername = "userdes";

	@Required
	private String cardifDatabasePassword = "userdes";

	@Required
	private String controleDatabaseUrl = "jdbc:sqlserver://SAOS005DB09D;databaseName=dbProdLife;portNumber=1433";

	@Required
	private String controleDatabaseUsername = "userdes";

	@Required
	private String controleDatabasePassword = "userdes";

	private static void initProperties() {
		try {
			internalProperties = new Properties();
			internalProperties.load(ClassName.class.getClassLoader().getResourceAsStream("internal.properties"));

			externalProperties = new Properties();
			externalProperties.load(ClassName.class.getClassLoader().getResourceAsStream("resources.properties"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		AppStandalone app = new AppStandalone();

		try {
			initProperties();

			app.setDatabaseDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			app.setDatabaseUrl(externalProperties.getProperty("db.url")); // "jdbc:sqlserver://ambrspapl2wd;databaseName=cardif-etl;portNumber=1433"
			app.setDatabaseUsername(externalProperties.getProperty("db.user")); // "userdes"
			app.setDatabasePassword(externalProperties.getProperty("db.password")); // "userdes"

			app.setCardifDatabaseUrl(externalProperties.getProperty("cardif.db.url")); // "jdbc:sqlserver://AMBRSPDB03WP;databaseName=dbGarantias;portNumber=1433"
			app.setCardifDatabaseUsername(externalProperties.getProperty("cardif.db.user")); // "userdes"
			app.setCardifDatabasePassword(externalProperties.getProperty("cardif.db.password")); // "userdes"

			app.setControleDatabaseUrl(externalProperties.getProperty("control.db.url")); // "jdbc:sqlserver://AMBRSPDB03WP;databaseName=dbGarantias;portNumber=1433"
			app.setControleDatabaseUsername(externalProperties.getProperty("control.db.user")); // "userdes"
			app.setControleDatabasePassword(externalProperties.getProperty("control.db.password")); // "userdes"

			/* Seta valores para select */
			app.setBoneco(args[0].toString()); /* nome boneco */
			app.setLayoutVersionId(args[1].toString()); /* layout a ser utilizado na extracao */

			app.setJobId(new Long(internalProperties.get("job.id").toString()));
			app.setUserId(new Long(internalProperties.get("job.user.id").toString()));
			app.setExecutionId(System.nanoTime());

			LOGGER.debug("##################################################################");
			LOGGER.debug("========== EXECUTANDO O JOB DO BONECO ABAIXO BOA SORTE. ==========");

			app.run();
			System.exit(0);

		} catch (Throwable t) {
			LOGGER.error(t.getMessage(), t);
			System.exit(1);
		}
	}

	public void run() throws JobException {
		JobParameters param = buildParameters();

		BatchStatus jobStatus = null;
		JobExecution jobExecution = null;
		CardifEtlUtils cardifEtlUtils = null;
		
		Job job = null;
		String errMsg = "";
		try {
			MDC.put("executionId", executionId);
			MDC.put("jobId", jobId);
			MDC.put("userId", userId);

			if (context == null) {
				LOGGER.debug("Iniciando criacao do contexto spring");
				try {
					context = new ClassPathXmlApplicationContext(new String[] { "classpath*:/META-INF/spring/batch/config-context-teste.xml" });
				} catch (Exception e) {
					throw new JobException("Nao foi possivel iniciar contexto spring (classpath*:/META-INF/spring/batch/config-context-teste.xml)", e);
				}
			}

			cardifEtlUtils = (CardifEtlUtils) context.getBean("cardifEtlUtils");
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			job = (Job) context.getBean("jobGrafica");
			LOGGER.warn("Iniciando execucao job " + job.getName());

			jobExecution = jobLauncher.run(job, param);
			jobStatus = jobExecution.getStatus();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			errMsg = e.getMessage() + ": " + ((e.getCause() != null) ? e.getCause().getMessage() : "sem causa definida");
		} finally {
			if (jobStatus != null && jobStatus.equals(BatchStatus.COMPLETED)) {
				cardifEtlUtils.done(executionId.intValue(), true);
			} else {
				errMsg = JobUtils.getExecutionErrors(jobExecution).concat(errMsg);
				cardifEtlUtils.done(executionId.intValue(), false);
				throw new JobException("Execucao do job " + job.getName() + " finalizada com erro: " + errMsg);
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

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public String getDatabaseUsername() {
		return databaseUsername;
	}

	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public String getControleDatabaseUrl() {
		return controleDatabaseUrl;
	}

	public void setControleDatabaseUrl(String controleDatabaseUrl) {
		this.controleDatabaseUrl = controleDatabaseUrl;
	}

	public String getControleDatabaseUsername() {
		return controleDatabaseUsername;
	}

	public void setControleDatabaseUsername(String controleDatabaseUsername) {
		this.controleDatabaseUsername = controleDatabaseUsername;
	}

	public String getControleDatabasePassword() {
		return controleDatabasePassword;
	}

	public void setControleDatabasePassword(String controleDatabasePassword) {
		this.controleDatabasePassword = controleDatabasePassword;
	}

	@ReplaceParam(name = "SEQ")
	public String sequence() {
		return "" + new Date().getTime();
	}

	@Override
	public String getDatabaseDriverClassName() {
		return this.databaseDriverClassName;
	}

	@Override
	public String getCardifDatabasePassword() {
		return this.cardifDatabasePassword;
	}

	public void setDatabaseDriverClassName(String driver) {
		this.databaseDriverClassName = driver;
	}

	public void setCardifDatabasePassword(String pwd) {
		this.cardifDatabasePassword = pwd;
	}

	public void setCardifDatabaseUsername(String usr) {
		this.cardifDatabaseUsername = usr;
	}

	public void setCardifDatabaseUrl(String url) {
		this.cardifDatabaseUrl = url;
	}

	public String getCardifDatabaseUrl() {
		return cardifDatabaseUrl;
	}

	public String getCardifDatabaseUsername() {
		return cardifDatabaseUsername;
	}
	
}
