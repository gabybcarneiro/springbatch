package br.com.cardif.etl.grafica.pojo;

import br.com.cardif.etl.grafica.annotations.LayoutField;

public class Capitalizacao {
			
	Integer serie;
	
	@LayoutField(nameField = "NUMERO_SORTE") 
	Integer numeroSorte;
	@LayoutField(nameField = "CAP_VIGENCIA") 
	String capVigencia;
	@LayoutField(nameField = "CAP_PERIODICIDADE") 
	String capPeriodicidade;
	@LayoutField(nameField = "CAP_PREMIO_BRUTO") 
	Double capPremioBruto;
	@LayoutField(nameField = "CAP_PREMIO_LIQUIDO") 
	Double capPremioLiquido;
	@LayoutField(nameField = "CAP_IR") 
	Double capIr;
	@LayoutField(nameField = "CAP_MODALIDADE")
	String capModalidade;
	
	public Integer getSerie() {
		return serie;
	}
	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	public Integer getNumeroSorte() {
		return numeroSorte;
	}
	public void setNumeroSorte(Integer numeroSorte) {
		this.numeroSorte = numeroSorte;
	}
	public String getCapVigencia() {
		return capVigencia;
	}
	public void setCapVigencia(String capVigencia) {
		this.capVigencia = capVigencia;
	}
	public String getCapPeriodicidade() {
		return capPeriodicidade;
	}
	public void setCapPeriodicidade(String capPeriodicidade) {
		this.capPeriodicidade = capPeriodicidade;
	}
	public Double getCapPremioBruto() {
		return capPremioBruto;
	}
	public void setCapPremioBruto(Double capPremioBruto) {
		this.capPremioBruto = capPremioBruto;
	}
	public Double getCapPremioLiquido() {
		return capPremioLiquido;
	}
	public void setCapPremioLiquido(Double capPremioLiquido) {
		this.capPremioLiquido = capPremioLiquido;
	}
	public Double getCapIr() {
		return capIr;
	}
	public void setCapIr(Double capIr) {
		this.capIr = capIr;
	}
	public String getCapModalidade() {
		return capModalidade;
	}
	public void setCapModalidade(String capModalidade) {
		this.capModalidade = capModalidade;
	}
}
