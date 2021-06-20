package com.unla.app.controllers.REST;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unla.app.entities.CodeReaders;
import com.unla.app.helpers.GeneratorHelper;
import com.unla.app.helpers.ResponseHelper;
import com.unla.app.models.CodeReadersModel;
import com.unla.app.models.PermisoDiarioModel;
import com.unla.app.models.PermisoModel;
import com.unla.app.models.PermisoPeriodoModel;
import com.unla.app.models.PersonaModel;
import com.unla.app.services.implementation.CodeReaderServices;
import com.unla.app.services.implementation.PermisosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PermisosController {
    @Autowired
	@Qualifier("permisosService")
	private PermisosServices permisoService;

    @Autowired
    private CodeReaderServices qrService;
        
    @RequestMapping(value = "/permisos/create", method = RequestMethod.POST)
    public ResponseHelper store(@RequestBody PermisoDiarioModel permisoModel,
    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {			
			return new ResponseHelper(400, bindingResult.toString());
		}        
          
        PermisoModel pm = permisoService.insertOrUpdate(permisoModel);

        GeneratorHelper gh = new GeneratorHelper();

        CodeReaders qr = new CodeReaders(gh.generateRandomString(10), pm.getIdPermiso());
        qrService.save(qr);       

        return new ResponseHelper(200, "" + qr.getCode());
    }

    @RequestMapping(value = "/permisos/create/temporario", method = RequestMethod.POST)
    public ResponseHelper store_temporario(@RequestBody PermisoPeriodoModel permisoPeriodoModel,
    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {			
			return new ResponseHelper(400, bindingResult.toString());
		}
        PermisoModel pm = permisoService.insertOrUpdate(permisoPeriodoModel);
        return new ResponseHelper(200, "" + pm.getIdPermiso());
    }

    @RequestMapping(value = "/permisos/userlist", method = RequestMethod.POST)
    public @ResponseBody List<String> trear_permisos(@RequestBody PersonaModel persona,
    BindingResult bindingResult) throws JsonProcessingException {
        
        List<String> newList = new ArrayList<String>();
       
		for (PermisoModel permisoModel : permisoService.findAll()) {
            if(permisoModel.getPersona().getDni() == persona.getDni()){

                CodeReaders qr = null;
                qr = qrService.findOneByPermisoId(permisoModel.getIdPermiso());

                CodeReadersModel qrModel = new CodeReadersModel();
                if(qr != null){
                    //convertir a modelo manual despues hacer el converter
                    qrModel.setCode(qr.getCode());
                    qrModel.setIdQr(qr.getIdQr());
                }

                permisoModel.setCodigoPermiso(qrModel);
                newList.add(permisoModel.toJson(permisoModel));
            }			
		}
        return newList;       
    }

    
}