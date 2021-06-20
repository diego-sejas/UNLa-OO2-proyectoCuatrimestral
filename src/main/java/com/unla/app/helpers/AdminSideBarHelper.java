package com.unla.app.helpers;

import java.util.ArrayList;
import java.util.List;
import com.unla.app.models.AdminSideBar;

public class AdminSideBarHelper {

    public List<AdminSideBar> lst_adminSideBar;

    public AdminSideBarHelper() {

        this.lst_adminSideBar = new ArrayList<AdminSideBar>();
        //Agregar Links NavBar aca
        AdminSideBar[] links = {
            new AdminSideBar(1, "/admin/dashboard", "fas fa-table", "Panel de Administración"),
            new AdminSideBar(2, "/admin/users", "fas fa-user", "Usuarios"),
            new AdminSideBar(3, "/admin/roles", "fas fa-users", "Roles"),
            new AdminSideBar(4, "/admin/permits", "fas fa-key", "Permisos") //terminar de implementar controller de permisos//
         
            
        };           
       this.createList(links);
    }

    private boolean createList(AdminSideBar[] links){
        for (AdminSideBar adminSideBar : links) {
            this.lst_adminSideBar.add(adminSideBar);
        }
        return true;
    }

    public List<AdminSideBar> getList() {
        return this.lst_adminSideBar;
    }   
    
}
