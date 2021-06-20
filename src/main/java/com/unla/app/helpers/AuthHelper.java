package com.unla.app.helpers;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public class AuthHelper {
    //Clase para Determinar si estas conectado o no
    private boolean loggedIn;
    private HttpSession session;

    public AuthHelper(HttpSession session){
        this.session = session;
        this.loggedIn = (this.session.getAttribute("USER") != null) ? true : false;
    }
    //Si no estas conectado te manda a que te conectes, si estas conectado seguis de largo
    public ModelAndView AuthMiddleware(ModelAndView mav){
        if(this.loggedIn){
            return mav;
        }
        return new ModelAndView("redirect:/login");
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public boolean getLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public HttpSession getSession() {
        return this.session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    
}
