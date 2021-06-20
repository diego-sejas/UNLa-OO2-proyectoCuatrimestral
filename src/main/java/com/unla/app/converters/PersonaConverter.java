package com.unla.app.converters;

import org.springframework.stereotype.Component;

import com.unla.app.entities.Personas;
import com.unla.app.models.PersonaModel;

@Component("personaConverter")
public class PersonaConverter {
	public PersonaModel entityToModel(Personas persona) {
		return new PersonaModel(persona.getIdPersona(), persona.getDni(), persona.getNombre(), persona.getApellido());
	}
	
	public Personas modelToEntity(PersonaModel personaModel) {
		return new Personas(personaModel.getIdPersona(), personaModel.getNombre(), personaModel.getApellido(), personaModel.getDni());
	}
}
