package br.com.cardif.etl.grafica.pojo;

import java.util.Date;
import java.util.List;

import br.com.cardif.etl.grafica.annotations.LayoutField;

public class Parcela {

	@LayoutField(nameField = "PERIODICIDADE") 
	String periodicidade;
	@LayoutField(nameField = "VALOR_PARCELA") 
	Double valorParcela;
	@LayoutField(nameField = "VALOR_TOTAL") 
	Double valorTotal;
	@LayoutField(nameField = "IOF")
	Double iof;
	
	Integer parcelaInicial;
	Integer totalParcela;

	@LayoutField(nameField={"DT_PAGAMENTO_1","DT_PAGAMENTO_2", "DT_PAGAMENTO_3", "DT_PAGAMENTO_4", "DT_PAGAMENTO_5", "DT_PAGAMENTO_6", "DT_PAGAMENTO_7", "DT_PAGAMENTO_8", "DT_PAGAMENTO_9", "DT_PAGAMENTO_10", "DT_PAGAMENTO_11", "DT_PAGAMENTO_12"} )
	List<Date> dataVencimento;

	public Double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getParcelaInicial() {
		return parcelaInicial;
	}

	public void setParcelaInicial(Integer parcelaInicial) {
		this.parcelaInicial = parcelaInicial;
	}

	public Integer getTotalParcela() {
		return totalParcela;
	}

	public void setTotalParcela(Integer totalParcela) {
		this.totalParcela = totalParcela;
	}

	public Double getIof() {
		return iof;
	}

	public void setIof(Double iof) {
		this.iof = iof;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}

	public List <Date> getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(List <Date> dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
}
