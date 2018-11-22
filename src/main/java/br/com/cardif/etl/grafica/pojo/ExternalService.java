package br.com.cardif.etl.grafica.pojo;

import java.sql.Date;

public class ExternalService {
	
	Integer id;
	String boneco;
	Integer tipoProduto;
	Long sequence;
	String fileName;
	String fileFolder;
	Date dtAtualizacao;
	
	public String getBoneco() {
		return boneco;
	}
	public void setBoneco(String boneco) {
		this.boneco = boneco;
	}
	public Integer getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(Integer tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileFolder() {
		return fileFolder;
	}
	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}
	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
}
