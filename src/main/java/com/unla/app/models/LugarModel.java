package com.unla.app.models;

import javax.validation.constraints.Size;

public class LugarModel {

	private long idLugar;
	
	@Size(min=3,max=20, message = "Debe tener entre 3 y 20 caracteres")
	private String lugar;
	
	@Size(min=4,max=5, message = "Debe tener entre 4 y 5 caracteres")
	private String codigoPostal;
	
	public LugarModel(){}

	
	public LugarModel(long idLugar, @Size(min = 3, max = 20) String lugar,
			@Size(min = 4, max = 5) String codigoPostal) {
		super();
		this.idLugar = idLugar;
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}

	public LugarModel(String lugar, String codigoPostal) {
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}

	public long getIdLugar() {
		return idLugar;
	}

	protected void setIdLugar(long idLugar) {
		this.idLugar = idLugar;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	@Override
	public String toString() {
		return "{" +
			" idLugar='" + getIdLugar() + "'" +
			", lugar='" + getLugar() + "'" +
			", codigoPostal='" + getCodigoPostal() + "'" +
			"}";
	}
	
}