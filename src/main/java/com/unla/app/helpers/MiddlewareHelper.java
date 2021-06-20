package com.unla.app.helpers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.unla.app.entities.Users;
import com.unla.app.entities.UsersRole;
import org.springframework.web.servlet.ModelAndView;

public class MiddlewareHelper {
    //Clase para Determinar si estas conectado o no
    private boolean loggedIn;
    private HttpSession session;
    
    public MiddlewareHelper(HttpSession session){
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

    //Roles Middleware
    public ModelAndView RoleMiddleware(ModelAndView mav, int minAdminLevel, List<UsersRole> roles){
        Users user = (Users) session.getAttribute("USER");
        System.out.println(user.getRoleAdminLevel(roles));
		if(user != null){
            if(user.getRoleAdminLevel(roles) >= minAdminLevel){
                return mav;
            }
        }
        return new ModelAndView("redirect:/403"); //forbidden
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
