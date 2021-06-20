package com.unla.app.converters;

import org.springframework.stereotype.Component;

import com.unla.app.entities.Rodados;
import com.unla.app.models.RodadoModel;

@Component("rodadoConverter")
public class RodadoConverter {
	
	public Rodados modelToEntity(RodadoModel rodadoModel) {
		
		return new Rodados(rodadoModel.getIdRodado(), rodadoModel.getDominio(), rodadoModel.getVehiculo());
	}
	
	public RodadoModel entityToModel(Rodados rodado) {
		
		return new RodadoModel(rodado.getIdRodado(),rodado.getDominio(),rodado.getVehiculo());
	}
	
}