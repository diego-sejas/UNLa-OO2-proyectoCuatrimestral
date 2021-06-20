package com.unla.app.models;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class PermisoDiarioModel extends PermisoModel {
	
	@NotEmpty()
	private String motivo;
	
	public PermisoDiarioModel() {
		super();
		motivo = new String();
	}

	public PermisoDiarioModel(int idPermiso, PersonaModel persona, Date fecha, List<LugarModel> desdeHasta, String motivo) {
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