package br.com.cardif.etl.grafica.pojo;

import br.com.cardif.etl.grafica.annotations.LayoutField;

public class OtherInfo {

	@LayoutField(nameField = "PLANO_ASSISTENCIA")
	String planoAssitencia;

	@LayoutField(nameField = "COSSEGURADORA_NOME")
	String cosseguradoraNome;
	@LayoutField(nameField = "COSSEGURADORA_CNJP")
	String cosseguradoraCNPJ;
	@LayoutField(nameField = "SEGURADORA_PARTICIPACAO")
	Double seguradoraParticipacao;
	@LayoutField(nameField = "COSSEGURADORA_PARTICIPACAO")
	Double cosseguradoraParticipacao;
	
	@LayoutField(nameField = "CAMPO_TEXTO1")
	String campoTexto1;
	@LayoutField(nameField = "CAMPO_TEXTO2")
	String campoTexto2;
	@LayoutField(nameField = "CAMPO_TEXTO3")
	String campoTexto3;
	@LayoutField(nameField = "CAMPO_NUMERICO1")
	Integer campoNumerico1;
	@LayoutField(nameField = "CAMPO_NUMERICO2")
	Integer campoNumerico2;
	@LayoutField(nameField = "CAMPO_NUMERICO3")
	Integer campoNumerico3;

	@LayoutField(nameField = "PROCESSO_SUSEP_01")
	Integer processoSusep1;
	@LayoutField(nameField = "PROCESSO_SUSEP_02")
	Integer processoSusep2;


	public String getPlanoAssitencia() {
		return planoAssitencia;
	}
	public void setPlanoAssitencia(String planoAssitencia) {
		this.planoAssitencia = planoAssitencia;
	}

	public String getCosseguradoraNome() {
		return cosseguradoraNome;
	}
	public void setCosseguradoraNome(String cosseguradoraNome) {
		this.cosseguradoraNome = cosseguradoraNome;
	}
	public String getCosseguradoraCNPJ() {
		return cosseguradoraCNPJ;
	}
	public void setCosseguradoraCNPJ(String cosseguradoraCNPJ) {
		this.cosseguradoraCNPJ = cosseguradoraCNPJ;
	}
	public Double getSeguradoraParticipacao() {
		return seguradoraParticipacao;
	}
	public void setSeguradoraParticipacao(Double seguradoraParticipacao) {
		this.seguradoraParticipacao = seguradoraParticipacao;
	}
	public Double getCosseguradoraParticipacao() {
		return cosseguradoraParticipacao;
	}
	public void setCosseguradoraParticipacao(Double cosseguradoraParticipacao) {
		this.cosseguradoraParticipacao = cosseguradoraParticipacao;
	}
	public String getCampoTexto1() {
		return campoTexto1;
	}
	public void setCampoTexto1(String campoTexto1) {
		this.campoTexto1 = campoTexto1;
	}
	public String getCampoTexto2() {
		return campoTexto2;
	}
	public void setCampoTexto2(String campoTexto2) {
		this.campoTexto2 = campoTexto2;
	}
	public String getCampoTexto3() {
		return campoTexto3;
	}
	public void setCampoTexto3(String campoTexto3) {
		this.campoTexto3 = campoTexto3;
	}
	public Integer getCampoNumerico1() {
		return campoNumerico1;
	}
	public void setCampoNumerico1(Integer campoNumerico1) {
		this.campoNumerico1 = campoNumerico1;
	}
	public Integer getCampoNumerico2() {
		return campoNumerico2;
	}
	public void setCampoNumerico2(Integer campoNumerico2) {
		this.campoNumerico2 = campoNumerico2;
	}
	public Integer getCampoNumerico3() {
		return campoNumerico3;
	}
	public void setCampoNumerico3(Integer campoNumerico3) {
		this.campoNumerico3 = campoNumerico3;
	}
	public Integer getProcessoSusep1() {
		return processoSusep1;
	}
	public void setProcessoSusep1(Integer processoSusep1) {
		this.processoSusep1 = processoSusep1;
	}
	public Integer getProcessoSusep2() {
		return processoSusep2;
	}
	public void setProcessoSusep2(Integer processoSusep2) {
		this.processoSusep2 = processoSusep2;
	}

	
}
