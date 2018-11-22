package br.com.cardif.etl.grafica.pojo;

import java.util.Date;

import br.com.cardif.etl.grafica.annotations.LayoutField;

public class Produto {
	
	@LayoutField(nameField = "CODIGO_PRODUTO") 
	Integer codigoProduto;
	@LayoutField(nameField = "MARCA_PRODUTO") 
	String marca;
	@LayoutField(nameField = "MODELO_PRODUTO") 
	String modelo;
	@LayoutField(nameField = "DESCRICAO_PRODUTO") 
	String descricao;
	@LayoutField(nameField = "NUMERO_IMEI") 
	Integer numeroImei;
	@LayoutField(nameField = "QUANTIDADE_PRODUTO") 
	Integer quantidade;
	@LayoutField(nameField = "INICIO_GARANTIA_FABRICA") 
	Date inicioGarantia;
	@LayoutField(nameField = "FIM_GARANTIA_FABRICA") 
	Date fimGarantia;
	@LayoutField(nameField = "VIGENCIA_GARANTIA_FABRICANTE") 
	Date vigenciaFabricante;
	
	public Integer getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getNumeroImei() {
		return numeroImei;
	}
	public void setNumeroImei(Integer numeroImei) {
		this.numeroImei = numeroImei;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Date getInicioGarantia() {
		return inicioGarantia;
	}
	public void setInicioGarantia(Date inicioGarantia) {
		this.inicioGarantia = inicioGarantia;
	}
	public Date getFimGarantia() {
		return fimGarantia;
	}
	public void setFimGarantia(Date fimGarantia) {
		this.fimGarantia = fimGarantia;
	}
	public Date getVigenciaFabricante() {
		return vigenciaFabricante;
	}
	public void setVigenciaFabricante(Date vigenciaFabricante) {
		this.vigenciaFabricante = vigenciaFabricante;
	}
	
}
