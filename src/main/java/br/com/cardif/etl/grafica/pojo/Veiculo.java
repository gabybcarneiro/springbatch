package br.com.cardif.etl.grafica.pojo;

import br.com.cardif.etl.grafica.annotations.LayoutField;

/*
* Tabelas: CAR_OBJ_INSURED / CAR_FIPE
* */
public class Veiculo {

	@LayoutField(nameField = "CAR_MARCA") 
	String marca;
	@LayoutField(nameField = "CAR_MODEL") 
	String modelo;
	@LayoutField(nameField = "CAR_CODIGO_REFERENCIA") 
	Integer codReferencia;
	@LayoutField(nameField = "CAR_ANO_MODELO") 
	String anoModelo;
	@LayoutField(nameField = "CAR_PLACA") 
	String placa;
	@LayoutField(nameField = "CAR_CHASSI_NUMBER") 
	String numeroChassi;
	@LayoutField(nameField = "CAR_RENAVAM") 
	String renavam;
	@LayoutField(nameField = "CAR_COMBUSTIVEL") 
	String combustivel;
	@LayoutField(nameField = "CAR_ZERO")
	String zero;
	@LayoutField(nameField = "CAR_COLOR") 
	String cor;
	@LayoutField(nameField = "CAR_CIRCULACAO") 
	String circulacao;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getCodReferencia() {
		return codReferencia;
	}
	public void setCodReferencia(Integer codReferencia) {
		this.codReferencia = codReferencia;
	}
	public String getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getNumeroChassi() {
		return numeroChassi;
	}
	public void setNumeroChassi(String numeroChassi) {
		this.numeroChassi = numeroChassi;
	}
	public String getRenavam() {
		return renavam;
	}
	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getZero() {
		return zero;
	}
	public void setZero(String zero) {
		this.zero = zero;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getCirculacao() {
		return circulacao;
	}
	public void setCirculacao(String circulacao) {
		this.circulacao = circulacao;
	}
	@Override
	public String toString() {
		return "Veiculo [marca=" + marca + ", modelo=" + modelo + ", codReferencia=" + codReferencia + ", anoModelo="
				+ anoModelo + ", placa=" + placa + ", numeroChassi=" + numeroChassi
				+ ", renavam=" + renavam + ", combustivel=" + combustivel + ", zero=" + zero + ", cor=" + cor
				+ ", circulacao=" + circulacao + "]";
	}
}
