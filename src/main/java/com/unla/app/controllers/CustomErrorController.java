package com.unla.app.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unla.app.entities.Users;
import com.unla.app.helpers.ConfigHelper;
import com.unla.app.helpers.RouteHelper;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
 
@RestController
public class CustomErrorController implements ErrorController {
  
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        view.addObject("appName", ConfigHelper.appName);
        Users user = (Users) session.getAttribute("USER");
        view.addObject("user", user);

        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {             
            view.addObject("title", "Página no encontrada | " + ConfigHelper.appName);
            view.setViewName(RouteHelper.ERROR_404);
        }
        else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            view.addObject("title", "Error 403 - " + ConfigHelper.appName);
            view.setViewName(RouteHelper.ERROR_403);
        }
        else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            view.addObject("title", "Error 500 - " + ConfigHelper.appName);
            view.setViewName(RouteHelper.ERROR_500);
        }
        else {
            view.addObject("title", "Error Desconocido - " + ConfigHelper.appName);
            view.setViewName(RouteHelper.ERROR);
        }
 
        return view;
    }

    @RequestMapping("/403")
    public ModelAndView handle403(HttpServletResponse response, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        view.addObject("appName", ConfigHelper.appName);
        Users user = (Users) session.getAttribute("USER");
        view.addObject("user", user);

        view.addObject("title", "Error 403 - " + ConfigHelper.appName);
        view.setViewName(RouteHelper.ERROR_403);

        return view;
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}