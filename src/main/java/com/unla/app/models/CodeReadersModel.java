package com.unla.app.models;

import javax.validation.constraints.Size;

public class CodeReadersModel {

	private long idQr;
		
	@Size(min=4, max=10, message = "Debe tener entre 4 y 10 caracteres")
	private String code;
	
	public CodeReadersModel() {
		
	}

	public CodeReadersModel(long idQr, String code) {
		this.idQr = idQr;
		this.code = code;
	}
	
	public CodeReadersModel(String code) {
		this.code = code;
	}

	public long getIdQr() {
		return this.idQr;
	}

	public void setIdQr(long idQr) {
		this.idQr = idQr;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}