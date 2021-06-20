package com.unla.app.controllers.REST;

import javax.validation.Valid;

import com.unla.app.entities.Personas;
import com.unla.app.services.IPersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permisos/")
public class PersonasController {

    @Autowired
    private IPersonaService personaService;

    @PostMapping({ "personas/create", "index", "personas.index" })
    public Personas store(@Valid Personas persona, BindingResult result, Model model) {
        // Falta Validar persona
        Personas existePersona = null;
        existePersona = personaService.findOneByDNI(persona.getDni());
        if (existePersona != null) {
            // Si ya existe la persona con el Mismo DNI (Levanta ese ID)
            persona = existePersona;
        }

        // Guarda la Persona
        personaService.save(persona);
        
        // Devuelve en JSON la Persona
        return persona;
    }

    @PostMapping({ "personas/show", "show", "personas.show" })
    public Personas show(@Valid Personas persona, BindingResult result, Model model) {        
        Personas buscarPersona = null;
        buscarPersona = personaService.findOneByDNI(persona.getDni());
        if(buscarPersona != null){
            return buscarPersona;         
        }        
        //devuelve persona vacia
        return new Personas();
    }

}