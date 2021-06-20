package com.unla.app.models;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

public class PermisoPeriodoModel extends PermisoModel{

	@Min(1)
	private int cantDias;
	private boolean vacaciones;
	@NotNull
	private RodadoModel rodado;

	public PermisoPeriodoModel() {
		super();
	}

	public PermisoPeriodoModel(int idPermiso, PersonaModel persona, Date fecha, List<LugarModel> desdeHasta,
			int cantDias, boolean vacaciones, RodadoModel rodado) {
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

	public RodadoModel getRodado() {
		return rodado;
	}

	public void setRodado(RodadoModel rodado) {
		this.rodado = rodado;
	}

	@Override
	public String toString() {
		return "{" +
			" cantDias='" + getCantDias() + "'" +
			", vacaciones='" + isVacaciones() + "'" +
			", rodado='" + getRodado() + "'" +
			"}";
	}

}