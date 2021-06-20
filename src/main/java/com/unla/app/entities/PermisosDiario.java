package com.unla.app.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "permisosDiario")
@PrimaryKeyJoinColumn(name = "idPermiso")
public class PermisosDiario extends Permisos {

	@Column(name = "motivo")
	private String motivo;

	public PermisosDiario() {

	}

	public PermisosDiario(int idPermiso, Personas persona, LocalDate fecha, Set<Lugares> desdeHasta, String motivo) {
		super(idPermiso, persona, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return "{" +
			" motivo='" + getMotivo() + "'" +
			"}";
	}

}
