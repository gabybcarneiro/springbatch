package br.com.cardif.etl.grafica.pojo;

import java.util.Date;

import br.com.cardif.etl.grafica.annotations.LayoutField;

public class DadosSegurado {

	@LayoutField(nameField = "IDENTIFICACAO_SECAO")
	String identSecao ;
	@LayoutField(nameField = "TIPO_IMPRESSAO")
	Integer impressao;
	@LayoutField(nameField = "APOLICE")
	Integer apolice;
	@LayoutField(nameField = "APOLICE_VARIAVEL")
	Integer apoliceVariavel;
	@LayoutField(nameField = "BONECO")
	String boneco ;
	@LayoutField(nameField = "NOME_SEGURADO")
	String nomeSegurado ;
	@LayoutField(nameField = "CPF_CNPJ")
	Long cpfCnpj;
	@LayoutField(nameField = "DTNASCIMENTO")
	Date dtNascimento;
	@LayoutField(nameField = "SEXO")
	String sexo;
	@LayoutField(nameField = "NACIONALIDADE")
	String nacionalidade;
	@LayoutField(nameField = "LOGRADOURO")
	String logradouro;
	@LayoutField(nameField = "COMPLEMENTO")
	String complemento;
	@LayoutField(nameField = "BAIRRO")
	String bairro;
	@LayoutField(nameField = "CIDADE")
	String cidade;
	@LayoutField(nameField = "ESTADO")
	String estado;
	@LayoutField(nameField = "CEP")
	Integer cep;
	@LayoutField(nameField = "EMAIL")
	String email;
	@LayoutField(nameField = "EMAIL_COMPLEMENTAR")
	String emailComplementar;
	@LayoutField(nameField = "CONTRATO")
	String contrato;
	@LayoutField(nameField = "CERTIFICADO")
	Integer certificado;
	@LayoutField(nameField = "CERTIFICATE_ACSELE")
	String certificadoAcsele;
	@LayoutField(nameField = "DATA_ADESAO")
	Date dtAdesao;
	@LayoutField(nameField = "INICIO_VIGENCIA")
	Date dtInicioVigencia;
	@LayoutField(nameField = "FIM_VIGENCIA")
	Date dtFimVigencia;
	@LayoutField(nameField = "NUMERO_CARTAO_SEGURADO") 
	String numeroCartaoSegurado;
	@LayoutField(nameField = "VALOR_FINANCIADO") 
	Double valorFinanciado;
	@LayoutField(nameField = "ENDERECO_RISCO")
	String enderecoRisco;
	@LayoutField(nameField = "COMPLEMENTO_RISCO")
	String complementoRisco;
	@LayoutField(nameField = "BAIRRO_RISCO")
	String bairroRisco;
	@LayoutField(nameField = "CIDADE_RISCO")
	String cidadeRisco;
	@LayoutField(nameField = "ESTADO_RISCO")
	String estadoRisco;
	@LayoutField(nameField = "CEP_RISCO")
	Integer cepRisco;

	Integer endosso;
	Integer endossoApolice;
	Integer idParceiro;
	Integer tipoProduto;
	String codigoProduto;
	
	public String getIdentSecao() {
		return identSecao;
	}
	public void setIdentSecao(String identSecao) {
		this.identSecao = identSecao;
	}
	public Integer getImpressao() {
		return impressao;
	}
	public void setImpressao(Integer impressao) {
		this.impressao = impressao;
	}
	public Integer getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(Integer tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public Integer getApolice() {
		return apolice;
	}
	public void setApolice(Integer apolice) {
		this.apolice = apolice;
	}
	public String getBoneco() {
		return boneco;
	}
	public void setBoneco(String boneco) {
		this.boneco = boneco;
	}
	public String getNomeSegurado() {
		return nomeSegurado;
	}
	public void setNomeSegurado(String nomeSegurado) {
		this.nomeSegurado = nomeSegurado;
	}
	public Long getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(Long l) {
		this.cpfCnpj = l;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailComplementar() {
		return emailComplementar;
	}
	public void setEmailComplementar(String emailComplementar) {
		this.emailComplementar = emailComplementar;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public Integer getCertificado() {
		return certificado;
	}
	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}
	public Integer getEndosso() {
		return endosso;
	}
	public void setEndosso(Integer endosso) {
		this.endosso = endosso;
	}
	public String getCertificadoAcsele() {
		return certificadoAcsele;
	}
	public void setCertificadoAcsele() {
		this.certificadoAcsele = "";
	}
	public Date getDtAdesao() {
		return dtAdesao;
	}
	public void setDtAdesao(Date dtAdesao) {
		this.dtAdesao = dtAdesao;
	}
	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}
	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}
	public Date getDtFimVigencia() {
		return dtFimVigencia;
	}
	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}
	public String getNumeroCartaoSegurado() {
		return numeroCartaoSegurado;
	}
	public void setNumeroCartaoSegurado(String numeroCartaoSegurado) {
		this.numeroCartaoSegurado = numeroCartaoSegurado;
	}
	public Integer getApoliceVariavel() {
		return apoliceVariavel;
	}
	public void setApoliceVariavel(Integer apoliceVariavel) {
		this.apoliceVariavel = apoliceVariavel;
	}
	public Double getValorFinanciado() {
		return valorFinanciado;
	}
	public void setValorFinanciado(Double valorFinanciado) {
		this.valorFinanciado = valorFinanciado;
	}

	public void setCertificadoAcsele(String certificadoAcsele) {
		this.certificadoAcsele = certificadoAcsele;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public Integer getIdParceiro() {
		return idParceiro;
	}
	public void setIdParceiro(Integer idParceiro) {
		this.idParceiro = idParceiro;
	}
	public Integer getEndossoApolice() {
		return endossoApolice;
	}
	public void setEndossoApolice(Integer endossoApolice) {
		this.endossoApolice = endossoApolice;
	}
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getEnderecoRisco() {
		return enderecoRisco;
	}
	public void setEnderecoRisco(String enderecoRisco) {
		this.enderecoRisco = enderecoRisco;
	}
	public String getComplementoRisco() {
		return complementoRisco;
	}
	public void setComplementoRisco(String complementoRisco) {
		this.complementoRisco = complementoRisco;
	}
	public String getBairroRisco() {
		return bairroRisco;
	}
	public void setBairroRisco(String bairroRisco) {
		this.bairroRisco = bairroRisco;
	}
	public String getCidadeRisco() {
		return cidadeRisco;
	}
	public void setCidadeRisco(String cidadeRisco) {
		this.cidadeRisco = cidadeRisco;
	}
	public String getEstadoRisco() {
		return estadoRisco;
	}
	public void setEstadoRisco(String estadoRisco) {
		this.estadoRisco = estadoRisco;
	}
	public Integer getCepRisco() {
		return cepRisco;
	}
	public void setCepRisco(Integer cepRisco) {
		this.cepRisco = cepRisco;
	}
}
