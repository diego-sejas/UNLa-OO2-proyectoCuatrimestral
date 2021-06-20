package com.unla.app.converters;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unla.app.entities.Lugares;
import com.unla.app.entities.PermisosDiario;
import com.unla.app.models.LugarModel;
import com.unla.app.models.PermisoDiarioModel;

@Component("permisoDiarioConverter")
public class PermisoDiarioConverter {

	@Autowired
	private LugarConverter lugarConverter;

	@Autowired
	private PersonaConverter personaConverter;

	public PermisosDiario modelToEntity(PermisoDiarioModel permisoModel) {
		Set<Lugares> lugares = new LinkedHashSet<Lugares>();

		for (LugarModel lugarModel : permisoModel.getDesdeHasta()) {
			lugares.add(lugarConverter.modelToEntity(lugarModel));
		}
		return new PermisosDiario(permisoModel.getIdPermiso(),
				personaConverter.modelToEntity(permisoModel.getPersona()), permisoModel.getFecha().toLocalDate(),
				lugares, permisoModel.getMotivo());
	}

	public PermisoDiarioModel entityToModel(PermisosDiario permiso) {
		List<LugarModel> lugares = new ArrayList<LugarModel>();
		for (Lugares lugar : permiso.getDesdeHasta()) {
			lugares.add(lugarConverter.entityToModel(lugar));
		}
		return new PermisoDiarioModel(permiso.getIdPermiso(), personaConverter.entityToModel(permiso.getPersona()),
				Date.valueOf(permiso.getFecha()), lugares, permiso.getMotivo());
	}

}
