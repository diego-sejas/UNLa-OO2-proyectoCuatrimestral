package com.unla.app.controllers.REST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unla.app.entities.Rodados;
import com.unla.app.services.IRodadoService;

@RestController
@RequestMapping("/permisos/")
public class RodadosController {
	
	@RestController
	@RequestMapping("/permisos/")
	public class PersonasController {

	    @Autowired
	    private IRodadoService rodadoService;

	    @PostMapping({ "rodados/create", "index", "permisos.index" })
	    public Rodados store(@Valid Rodados rodado, BindingResult result, Model model) {
	        // Falta Validar Rodado
	        Rodados existeRodado = null;
	        existeRodado = rodadoService.findOneByDominio(rodado.getDominio());
	        if (existeRodado != null) {
	            // Si ya existe el Rodado con el Mismo Dominio (Levanta ese Dominio)
	            rodado = existeRodado;
	        }

	        // Guarda el Rodado
	        rodadoService.save(rodado);
	        
	        // Devuelve en JSON el Rodado
	        return rodado;
	    }
	}
}
