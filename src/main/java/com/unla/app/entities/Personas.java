package com.unla.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long idPersona;

	@Column(name = "nombre")
	protected String nombre;

	@Column(name = "apellido")
	protected String apellido;

	@Column(name = "dni", unique = true)
	protected int dni;

	@Column(name = "createdat")
	@CreationTimestamp
	protected LocalDateTime createAt;

	@Column(name = "updatedat")
	@UpdateTimestamp
	protected LocalDateTime updateAt;

	public Personas() {
	}

	public Personas(String nombre, String apellido, int dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public Personas(long idPersona, String nombre, String apellido, int dni) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
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

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "{" + " idPersona='" + getIdPersona() + "'" + ", nombre='" + getNombre() + "'" + ", apellido='"
				+ getApellido() + "'" + ", dni='" + getDni() + "'" + ", createAt='" + getCreateAt() + "'"
				+ ", updateAt='" + getUpdateAt() + "'" + "}";
	}

}
