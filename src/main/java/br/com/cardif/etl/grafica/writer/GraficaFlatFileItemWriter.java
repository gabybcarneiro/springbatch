package br.com.cardif.etl.grafica.writer;

import br.com.cardif.etl.exception.EtlException;
import br.com.cardif.etl.grafica.dao.ExternalServiceDAO;
import br.com.cardif.etl.grafica.pojo.ExternalService;
import br.com.cardif.etl.jobs.JobException;
import br.com.cardif.etl.jobs.JobUtils;
import br.com.cardif.etl.jobs.LayoutDAO;
import br.com.cardif.etl.jobs.RowTypeEnum;
import br.com.cardif.etl.jobs.spring.EtlLayoutLineAggregator;
import br.com.cardif.etl.model.LayoutType;
import br.com.cardif.etl.model.LayoutVersion;
import br.com.cardif.etl.model.RowType;
import br.com.cardif.etl.util.CardifEtlUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Estende a classe FlatFileItemWriter para implementar funcionalidades especificas ao sistema ETL
 */
public class GraficaFlatFileItemWriter implements ItemWriter<Map<String, Object>>, FlatFileFooterCallback, ItemStream {

	private static final Logger LOGGER = Logger.getLogger(br.com.cardif.etl.grafica.writer.GraficaFlatFileItemWriter.class);

	protected FlatFileItemWriter<Map<String, Object>> delegate;

	protected LayoutDAO layoutDAO;

	protected CardifEtlUtils cardifEtlUtils;

	protected Long layoutVersionId;

	protected LayoutType layoutType;

	protected LayoutVersion layoutVersion = new LayoutVersion();

	protected Resource resource;

	protected long totalLinhas = 0;

	protected boolean fakeAdd = false;

	protected String countHeaderTrailler;

	protected EtlLayoutLineAggregator lineAggregator = null;

	private final String FILE_NAME = "/opt/etl/extratores/output/grafica/MS.CER.REM.{BONECO}_{yyyyMMdd}.{SEQUENCIAL}.CDF";

	@Autowired
	ExternalServiceDAO externalServiceDAO;

	/**
	 * Inicializa os campos layoutType e layoutVersion. Eh a partir deles que o job identifica que layout sera utilizado na formatacao do arquivo de saida.
	 */
	public void init() {
		LOGGER.debug("Utilizando layoutVersionId = " + ((layoutVersionId != null) ? layoutVersionId : "null"));
		try {
			layoutType = layoutDAO.getLayoutTypeByLayoutVersionId(layoutVersionId);
		} catch (JobException e) {
			LOGGER.error(e.getMessage(), e);
		}

		layoutVersion.setLayoutVersionId(layoutVersionId);
	}

	/**
	 * Quem efetivamente gera o arquivo de saida eh o delegate (que eh um FlatFileItemWriter padrao).
	 * @see ItemWriter#write(List)
	 */
	@Override
	public void write(List<? extends Map<String, Object>> items) throws Exception {
		try {
			LOGGER.debug("Chunk size: " + items.size());
			// Eh esta chamada que efetivamente gera o arquivo de saida
			if(!fakeAdd) delegate.write(preencheDadosFake(items.get(0)));
			delegate.write(items);
			totalLinhas += items.size() + 2;
			LOGGER.error("Total acumulado de linhas: " + totalLinhas);
		} catch (Exception e) {
			throw new EtlException("Erro ao executar delegate.write(items): " + e.getMessage(), e);
		}
	}

	@Override
	public void writeFooter(Writer writer) throws IOException {
		LOGGER.error("Iniciando escrita footer...");

		StringBuilder footer = new StringBuilder();
		// TODO Deixar parametrizavel. E se algum parceiro tiver outro tipo de numeracao?
		footer.append("9");
		for (int i = String.valueOf(totalLinhas).length(); i < 8; i++) {
			footer.append("0");
		}
		footer.append(totalLinhas);
		writer.write(footer.toString());

		RowType rowType = new RowType();
		rowType.setRowTypeId(RowTypeEnum.TRAILLER.getRowTypeId());
		LOGGER.warn("Footer escrito: " + footer.toString());
	}

	private List<Map<String, Object>> preencheDadosFake(Map<String, Object> items) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

		Map<String, Object> dadosFake1 = new HashMap<>();
		dadosFake1.putAll(items);
		dadosFake1.put("CONTRATO", 1);
		dadosFake1.put("NOME_SEGURADO", "CARDIF - PRODUTOS");
		dadosFake1.put("LOGRADOURO", "Av. Pres. Juscelino Kubitschek 1909, Torre Sul");
		dadosFake1.put("COMPLEMENTO", "8º Andar");
		dadosFake1.put("BAIRRO", "Vila Nova Conceição");
		dadosFake1.put("CIDADE", "São Paulo");
		dadosFake1.put("ESTADO", "SP");
		dadosFake1.put("CEP", "04543907");
		dadosFake1.put("EMAIL", "implantacaodeprodutos@cardif.com.br");
		dadosFake1.put("EMAIL_COMPLEMENTAR", "implantacaodeprodutos@cardif.com.br");

		Map<String, Object> dadosFake2 = new HashMap<>();
		dadosFake2.putAll(items);
		dadosFake2.put("CONTRATO", 2);
		dadosFake2.put("NOME_SEGURADO", "CARDIF - PRODUTOS");
		dadosFake2.put("LOGRADOURO", "Av. Pres. Juscelino Kubitschek 1909, Torre Sul");
		dadosFake2.put("COMPLEMENTO", "8º Andar");
		dadosFake2.put("BAIRRO", "Vila Nova Conceição");
		dadosFake2.put("CIDADE", "São Paulo");
		dadosFake2.put("ESTADO", "SP");
		dadosFake2.put("CEP", "04543907");
		dadosFake2.put("EMAIL", "");
		dadosFake2.put("EMAIL_COMPLEMENTAR", "");

		list.add(dadosFake1);
		list.add(dadosFake2);

		fakeAdd = true;
		return list;
	}

	/**
	 * Define o EtlLayoutLineAggregator necessario para o funcionamento do GraficaFlatFileItemWriter, bem como o Resource este metodo eh
	 * executado antes do inicio do step do springbatch (anotacao @BeforeStep) Para funcionar, o metodo recupera dados de tabela de configuração.
	 * 
	 * @param stepExecution
	 */
	@BeforeStep
	public void defineItemWriterProperties(StepExecution stepExecution) throws Exception {
		LOGGER.error("defineItemWriterProperties...");
		ExternalService es = null;
		String outputFileName = null;

		JobExecution jobExecution = stepExecution.getJobExecution();
		JobParameter boneco = jobExecution.getJobParameters().getParameters().get("boneco");

		if (boneco != null && boneco.getValue() != null && !boneco.toString().isEmpty()) {
			es = externalServiceDAO.getExternalService(boneco.toString());
		}

		JobParameter jp = jobExecution.getJobParameters().getParameters().get("layoutVersionId");
		if (jp != null && jp.getValue() != null) {
			this.layoutVersionId = Long.valueOf(jp.getValue().toString());
		}

		if (es != null && es.getFileName() != null) {
			outputFileName = es.getFileFolder() + es.getFileName();
		} else {
			outputFileName = FILE_NAME;
		}

        outputFileName = outputFileName.replaceAll("[{](SEQUENCIAL)[}\\]]", JobUtils.preencheZerosEsquerda(es.getSequence().toString(), 5));
		outputFileName = outputFileName.replaceAll("[{](BONECO)[}\\]]", es.getBoneco());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataAtual =  sdf.format(new Date());
		outputFileName = outputFileName.replaceAll("[{](yyyyMMdd)[}\\]]", dataAtual);

		this.init();
		this.defineLineAggregator();

		if (lineAggregator != null) {
			delegate.setLineAggregator(lineAggregator);
		}
		delegate.setResource(new FileSystemResource(outputFileName));
		LOGGER.error("defineItemWriterProperties...fim");
	}

	protected void defineLineAggregator() {
		try {
			lineAggregator = new EtlLayoutLineAggregator(getLayoutDAO(), getLayoutVersionId(), RowTypeEnum.DETAIL);
		} catch (EtlException e) {
//			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		delegate.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		delegate.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
	}

	public LayoutDAO getLayoutDAO() {
		return layoutDAO;
	}

	public void setLayoutDAO(LayoutDAO layoutDAO) {
		this.layoutDAO = layoutDAO;
	}

	public Long getLayoutVersionId() {
		return layoutVersionId;
	}

	public void setLayoutVersionId(Long layoutVersionId) {
		this.layoutVersionId = layoutVersionId;
	}

	public FlatFileItemWriter<Map<String, Object>> getDelegate() {
		return delegate;
	}

	public void setDelegate(FlatFileItemWriter<Map<String, Object>> delegate) {
		this.delegate = delegate;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public LayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}

	public CardifEtlUtils getCardifEtlUtils() {
		return cardifEtlUtils;
	}

	public void setCardifEtlUtils(CardifEtlUtils cardifEtlUtils) {
		this.cardifEtlUtils = cardifEtlUtils;
	}

	public long getTotalLinhas() {
		return totalLinhas;
	}

	public void setTotalLinhas(long totalLinhas) {
		this.totalLinhas = totalLinhas;
	}

	public String getCountHeaderTrailler() {
		return countHeaderTrailler;
	}

	public void setCountHeaderTrailler(String countHeaderTrailler) {
		this.countHeaderTrailler = countHeaderTrailler;
	}

}