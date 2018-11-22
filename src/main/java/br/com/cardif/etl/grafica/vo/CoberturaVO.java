package br.com.cardif.etl.grafica.vo;

public class CoberturaVO {
	
	private Integer id;
	private String planoContratado;
	private String risco;
	private String cobertura;
	private Double premioCobertura;
	private Integer lmiCobertura;
	private Integer lmiParcela;
	private Double lmiPercentual;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlanoContratado() {
		return planoContratado;
	}
	public void setPlanoContratado(String planoContratado) {
		this.planoContratado = planoContratado;
	}
	public String getRisco() {
		return risco;
	}
	public void setRisco(String risco) {
		this.risco = risco;
	}
	public String getCobertura() {
		return cobertura;
	}
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}
	public Double getPremioCobertura() {
		return premioCobertura;
	}
	public void setPremioCobertura(Double premioCobertura) {
		this.premioCobertura = premioCobertura;
	}
	public Integer getLmiCobertura() {
		return lmiCobertura;
	}
	public void setLmiCobertura(Integer lmiCobertura) {
		this.lmiCobertura = lmiCobertura;
	}
	public Integer getLmiParcela() {
		return lmiParcela;
	}
	public void setLmiParcela(Integer lmiParcela) {
		this.lmiParcela = lmiParcela;
	}
	public Double getLmiPercentual() {
		return lmiPercentual;
	}
	public void setLmiPercentual(Double lmiPercentual) {
		this.lmiPercentual = lmiPercentual;
	}
}
