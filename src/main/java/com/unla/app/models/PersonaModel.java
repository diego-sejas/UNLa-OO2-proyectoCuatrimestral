package com.unla.app.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonaModel {
	protected long idPersona;
	
	@NotNull()
	@Min(value=8)
	protected int dni;
	
	@NotNull()
	@Size(min=3,max=20)
	protected String nombre;
	
	@NotNull()
	@Size(min=3,max=20)
	protected String apellido;
	
	public PersonaModel() {}
	
	public PersonaModel(long idPersona, @Min(6) int dni, @Size(min = 3, max = 20) String nombre,
			@Size(min = 3, max = 20) String apellido) {
		super();
		this.idPersona = idPersona;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public PersonaModel(@Min(6) int dni, @Size(min = 3, max = 20) String nombre,
			@Size(min = 3, max = 20) String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	

	@Override
	public String toString() {
		return "{" +
			" idPersona='" + getIdPersona() + "'" +
			", dni='" + getDni() + "'" +
			", nombre='" + getNombre() + "'" +
			", apellido='" + getApellido() + "'" +
			"}";
	}
	
	
}
