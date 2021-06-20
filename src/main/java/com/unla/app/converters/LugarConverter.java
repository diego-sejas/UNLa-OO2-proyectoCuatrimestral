package com.unla.app.converters;

import org.springframework.stereotype.Component;

import com.unla.app.entities.Lugares;
import com.unla.app.models.LugarModel;

@Component("lugarConverter")
public class LugarConverter {
	
	public Lugares modelToEntity(LugarModel lugarModel) {
		
		return new Lugares(lugarModel.getIdLugar(), lugarModel.getLugar(), lugarModel.getCodigoPostal());
	}
	
	public LugarModel entityToModel(Lugares lugar) {
		
		return new LugarModel(lugar.getIdLugar(),lugar.getLugar(),lugar.getCodigoPostal());
	}

}