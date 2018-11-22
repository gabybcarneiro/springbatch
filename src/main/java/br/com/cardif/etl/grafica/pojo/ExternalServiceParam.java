package br.com.cardif.etl.grafica.pojo;

import java.sql.Date;

public class ExternalServiceParam {
	
	Integer id;
	Integer exId;
	String paramChave;
	String paramValor;
	Date dtInsertParam;
	String insertUserId;
	Date dtUpdateParam;
	String updateUserId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExId() {
		return exId;
	}

	public void setExId(Integer exId) {
		this.exId = exId;
	}

	public String getParamChave() {
		return paramChave;
	}

	public void setParamChave(String paramChave) {
		this.paramChave = paramChave;
	}

	public String getParamValor() {
		return paramValor;
	}

	public void setParamValor(String paramValor) {
		this.paramValor = paramValor;
	}

	public Date getDtInsertParam() {
		return dtInsertParam;
	}

	public void setDtInsertParam(Date dtInsertParam) {
		this.dtInsertParam = dtInsertParam;
	}

	public String getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}

	public Date getDtUpdateParam() {
		return dtUpdateParam;
	}

	public void setDtUpdateParam(Date dtUpdateParam) {
		this.dtUpdateParam = dtUpdateParam;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
}
