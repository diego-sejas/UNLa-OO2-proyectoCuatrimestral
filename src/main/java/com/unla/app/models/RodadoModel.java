package com.unla.app.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RodadoModel {
	
	private long idRodado;
	
	@NotNull()
	@Pattern(regexp = "^[A-Z]{3}[0-9]{3}|[A-Z]{2}[0-9]{3}[A-Z]{2}$", message ="El Nro de Patente tiene mal formato (XXX000 - XX000XX)")
	private String dominio;
	
	@NotNull()
	@Size(min=5, max=50)
	private String vehiculo;
	
	public RodadoModel() {}
	
	public RodadoModel(long idRodado, @Size(min = 6, max = 10) String dominio,
			@Size(min = 5, max = 50) String vehiculo) {
		super();
		this.idRodado = idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
	}

	public RodadoModel(String dominio, String vehiculo)
	{
		super();
		this.dominio=dominio;
		this.vehiculo=vehiculo;
	}

	public long getIdRodado() {
		return idRodado;
	}

	public void setIdRodado(long idRodado) {
		this.idRodado = idRodado;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {		
		return "{" +
			" idRodado='" + getIdRodado() + "'" +
			", dominio='" + getDominio() + "'" +
			", vehiculo='" + getVehiculo() + "'" +
			"}";
	}
	
}