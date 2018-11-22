package br.com.cardif.etl.grafica.vo;

import java.util.Date;

public class BeneficiarioVO {
	
	private Integer id;
	private String nomeBeneficiario;
	private Double percentualBeneficiario;
	private Date dataNascimentoBenef;
	private String grauParentesco;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}
	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}
	public Double getPercentualBeneficiario() {
		return percentualBeneficiario;
	}
	public void setPercentualBeneficiario(Double percentualBeneficiario) {
		this.percentualBeneficiario = percentualBeneficiario;
	}
	public Date getDataNascimentoBenef() {
		return dataNascimentoBenef;
	}
	public void setDataNascimentoBenef(Date dataNascimentoBenef) {
		this.dataNascimentoBenef = dataNascimentoBenef;
	}
	public String getGrauParentesco() {
		return grauParentesco;
	}
	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}
}
