package br.com.cardif.etl.jobs;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cardif.etl.grafica.writer.GraficaFlatFileItemWriter;
import org.apache.log4j.Logger;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cardif.etl.grafica.dao.ExternalServiceDAO;
import br.com.cardif.etl.jobs.spring.EtlLayoutLineAggregator;
import br.com.cardif.etl.model.Layout;

/**
 * Implementa um FlatFileHeaderCallback, que recupera do banco de dados as colunas e os dados a serem incluidos no header do arquivo.
 */
public class EtlFlatFileHeaderCallback extends GraficaFlatFileItemWriter implements FlatFileHeaderCallback, FlatFileFooterCallback {
	private static final Logger LOGGER = Logger.getLogger(EtlFlatFileHeaderCallback.class);

	@Autowired
	ExternalServiceDAO externalServiceDAO;
	
	private LayoutDAO layoutDAO = null;

	private Long layoutVersionId = null;
    private List<Layout> layoutColumnsHeader = null;
    private List<Layout> layoutColumnsFooter = null;

	private String boneco;
	private Integer totalLinhas = 0;

	public void init() {
		try {
			layoutColumnsHeader = getLayoutDAO().getLayoutByVersionAndRowType(getLayoutVersionId(), RowTypeEnum.HEADER.getRowTypeId());
		} catch (JobException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Na implementacao do metodo utiliza a classe EtlLayoutLineAggregator para formatar a linha que sera incluida como header do arquivo
	 */
	public void writeHeader(Writer writer) throws IOException {
		LOGGER.error("EtlFlatFileHeaderCallback -> Iniciando escrita do header...");

		EtlLayoutLineAggregator aggregator = new EtlLayoutLineAggregator();
		aggregator.setLayoutColumns(layoutColumnsHeader);
				
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("NOME_ARQUIVO", "");
		parametros.put("SEQUENCIAL", externalServiceDAO.getSequence(this.getBoneco()));
		parametros.put("IDENTIFICACAO_SECAO", "0");
		parametros.put("TIPO_ARQUIVO", "1");
		parametros.put("ORIGEM_ARQUIVO", "CARDIF");
		parametros.put("NOME_GRAFICA", "IGB");
		parametros.put("FILLER", "");
		parametros.put("DATA_MOVIMENTACAO", new Date());

		String header = aggregator.aggregate(parametros);
		LOGGER.debug("Header:" + header);
		writer.write(header);
	}

    @Override
    public void writeFooter(Writer writer) throws IOException {
        LOGGER.warn("Iniciando escrita do footer...");

        EtlLayoutLineAggregator aggregator = new EtlLayoutLineAggregator();
        aggregator.setLayoutColumns(layoutColumnsFooter);

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IDREGISTRO", "9");
        parametros.put("TOTREGISTROS", totalLinhas);
        parametros.put("FILLER", "");
        
        String footer = aggregator.aggregate(parametros);
        LOGGER.debug("Footer:" + footer);
        writer.write(footer);
    }

    public Long getLayoutVersionId() {
		return layoutVersionId;
	}

	public void setLayoutVersionId(Long layoutVersionId) {
		this.layoutVersionId = layoutVersionId;
	}

	public LayoutDAO getLayoutDAO() {
		return layoutDAO;
	}

	public void setLayoutDAO(LayoutDAO layoutDAO) {
		this.layoutDAO = layoutDAO;
	}

	public String getBoneco() {
		return boneco;
	}

	public void setBoneco(String boneco) {
		this.boneco = boneco;
	}

    public long getTotalLinhas() {
        return totalLinhas;
    }

    public void setTotalLinhas(Integer totalLinhas) {
        this.totalLinhas = totalLinhas;
    }
	
}
