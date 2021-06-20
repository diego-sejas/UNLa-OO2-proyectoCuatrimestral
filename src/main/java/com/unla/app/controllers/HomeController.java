package com.unla.app.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.unla.app.entities.CodeReaders;
import com.unla.app.entities.Lugares;
import com.unla.app.entities.Users;
import com.unla.app.helpers.ConfigHelper;
import com.unla.app.helpers.RouteHelper;
import com.unla.app.models.PermisoModel;
import com.unla.app.services.ICodeReaderService;
import com.unla.app.services.ILugarService;
import com.unla.app.services.implementation.PermisosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ILugarService lugarService;
    
    @Autowired
    private ICodeReaderService qrService;

    @Autowired
	@Qualifier("permisosService")
	private PermisosServices permisoService;
    
    @GetMapping({"/", "index" , "home"})
    public ModelAndView index(HttpSession session) {
        Users user = (Users) session.getAttribute("USER");
        
        ModelAndView view = new ModelAndView(RouteHelper.INDEX);
        view.addObject("title", "Inicio - " + ConfigHelper.appName);
        view.addObject("appName", ConfigHelper.appName);
        view.addObject("user", user);
        return view;
    }   

    @GetMapping({"/permisos", "index", "permisos.index"})
    public ModelAndView index() {       
        ModelAndView view = new ModelAndView(RouteHelper.PERMISOS);
        view.addObject("title", "Solicitar Permiso - " + ConfigHelper.appName);
        view.addObject("appName", ConfigHelper.appName);

        //Agregamos Lugar
        List<Lugares> lugares = lugarService.findAll();
        view.addObject("lugares", lugares);
        return view;
    }   

    @RequestMapping(path="permisos/{code}")
    public ModelAndView permiso_view(@PathVariable("code") String code) {
        ModelAndView view = new ModelAndView(RouteHelper.PERMISOS_SHOW);
        CodeReaders qr = qrService.findOneByCode(code);
        if(qr == null){
            //Si el codigo no existe vuelve al home
            return new ModelAndView("redirect:/");
        }
        view.addObject("title", "Permiso Nº " + qr.getPermisoId() +  " - " + ConfigHelper.appName);
        view.addObject("appName", ConfigHelper.appName);
        view.addObject("qrcode", qr.getCode());
       
        PermisoModel permisoModel = permisoService.findById(Math.toIntExact(qr.getPermisoId())); 
        view.addObject("permiso", permisoModel);
        return view;
    }

}
