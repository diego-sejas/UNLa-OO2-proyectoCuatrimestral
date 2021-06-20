package com.unla.app.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "permisosPeriodo")
@PrimaryKeyJoinColumn(name = "idPermiso")
public class PermisosPeriodo extends Permisos {

	@Column(name = "cantDias")
	private int cantDias;

	@Column(name = "vacaciones")
	private boolean vacaciones;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "idRodado", nullable = false)
	private Rodados rodado;

	public PermisosPeriodo() {

	}

	public PermisosPeriodo(int idPermiso, Personas persona, LocalDate fecha, Set<Lugares> desdeHasta, int cantDias,
			boolean vacaciones, Rodados rodado) {
		super(idPermiso, persona, fecha, desdeHasta);
		this.cantDias = cantDias;
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}

	public int getCantDias() {
		return cantDias;
	}

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}

	public boolean isVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(boolean vacaciones) {
		this.vacaciones = vacaciones;
	}

	public Rodados getRodado() {
		return rodado;
	}

	public void setRodado(Rodados rodado) {
		this.rodado = rodado;
	}

	@Override
	public String toString() {
		return "{" + " cantDias='" + getCantDias() + "'" + ", vacaciones='" + isVacaciones() + "'" + ", rodado='"
				+ getRodado() + "'" + "}";
	}

}
