package br.com.cardif.etl.grafica.pojo;

import br.com.cardif.etl.grafica.annotations.LayoutField;

public class Estipulante {
	
	
	@LayoutField(nameField = "ESTIPULANTE")
	String nome;
	@LayoutField(nameField = "CNPJ_ESTIPULANTE")
	String cnpj;
	@LayoutField(nameField = "PERCENTUAL_PROLABORE_ESTIPULANTE")
	Double percentProLabore;
	@LayoutField(nameField = "VALOR_PROLABORE_ESTIPULANTE")
	Double valorProlabore;
	@LayoutField(nameField = "ENDERECO_ESTIPULANTE") 
	String endereco;
	@LayoutField(nameField = "COMPLEMENTO_ESTIPULANTE") 
	String complemento;
	@LayoutField(nameField = "BAIRRO_ESTIPULANTE") 
	String bairro;
	@LayoutField(nameField = "CIDADE_ESTIPULANTE") 
	String cidade;
	@LayoutField(nameField = "ESTADO_ESTIPULANTE") 
	String estado;
	@LayoutField(nameField = "CEP_ESTIPULANTE") 
	Integer cep;
	@LayoutField(nameField = "DDD_TELEFONE_ESTIPULANTE") 
	Integer ddd;
	@LayoutField(nameField = "TELEFONE_ESTIPULANTE") 
	Integer telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Double getPercentProLabore() {
		return percentProLabore;
	}
	public void setPercentProLabore(Double percentProLabore) {
		this.percentProLabore = percentProLabore;
	}
	public Double getValorProlabore() {
		return valorProlabore;
	}
	public void setValorProlabore(Double valorProlabore) {
		this.valorProlabore = valorProlabore;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getCep() {
		return cep;
	}
	public void setCep(Integer cep) {
		this.cep = cep;
	}
	public Integer getDdd() {
		return ddd;
	}
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
}
