package com.unla.app.converters;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unla.app.entities.Lugares;
import com.unla.app.entities.PermisosPeriodo;
import com.unla.app.models.LugarModel;
import com.unla.app.models.PermisoPeriodoModel;

@Component("permisoPeriodoConverter")
public class PermisoPeriodoConverter {
	
	@Autowired
	private LugarConverter lugarConverter;
	
	@Autowired
	private PersonaConverter personaConverter;
	
	@Autowired
	private RodadoConverter rodadoConverter;
	
	public PermisosPeriodo modelToEntity(PermisoPeriodoModel permisoModel) {
		Set<Lugares> lugares=new LinkedHashSet<Lugares>();
		for(LugarModel lugarModel : permisoModel.getDesdeHasta()) {
			lugares.add(lugarConverter.modelToEntity(lugarModel));
		}
		return new PermisosPeriodo(permisoModel.getIdPermiso(),
				personaConverter.modelToEntity(permisoModel.getPersona())
				, permisoModel.getFecha().toLocalDate(), lugares,permisoModel.getCantDias(), permisoModel.isVacaciones(), 
				rodadoConverter.modelToEntity(permisoModel.getRodado()));
	}
	
	public  PermisoPeriodoModel entityToModel(PermisosPeriodo permiso) {
		List<LugarModel> lugares=new ArrayList<LugarModel>();
		for(Lugares lugar : permiso.getDesdeHasta()) {
			lugares.add(lugarConverter.entityToModel(lugar));
		}
		return new PermisoPeriodoModel(permiso.getIdPermiso(),
				personaConverter.entityToModel(permiso.getPersona())
				,Date.valueOf(permiso.getFecha())
				,lugares, permiso.getCantDias(), permiso.isVacaciones(),
				rodadoConverter.entityToModel(permiso.getRodado()));
	}

}
