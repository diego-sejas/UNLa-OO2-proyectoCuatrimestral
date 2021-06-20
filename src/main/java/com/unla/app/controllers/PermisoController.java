package com.unla.app.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.app.entities.Users;
import com.unla.app.entities.UsersRole;
import com.unla.app.helpers.AdminSideBarHelper;
import com.unla.app.helpers.ConfigHelper;
import com.unla.app.helpers.MiddlewareHelper;
import com.unla.app.helpers.RouteHelper;
import com.unla.app.services.IUserRoleService;

@Controller
public class PermisoController {
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@GetMapping({ "/admin/permits", "permisos", "permisos" })
	public ModelAndView permisos(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpSession session) {
		ModelAndView view = new ModelAndView(RouteHelper.DASHBOARD_PERMITS);
		AdminSideBarHelper sideBar = new AdminSideBarHelper();

		String pageName = "Permisos";
		view.addObject("title", pageName + " - " + ConfigHelper.appName);
		view.addObject("pageName", pageName);
		Users user = (Users) session.getAttribute("USER");
		if (user != null) {
			view.addObject("userName", user.getFirstName() + " " + user.getLastName());
		}
		view.addObject("appName", ConfigHelper.appName);

		view.addObject("sideBarLink", 4); // ID del link para que quede en azul (activo) en el menu izquierdo
		view.addObject("sideBar", sideBar.lst_adminSideBar);


		List<UsersRole> roles = userRoleService.findAll();
		MiddlewareHelper mHelper = new MiddlewareHelper(session);
		return mHelper.AuthMiddleware(mHelper.RoleMiddleware(view, 25, roles));
	}


}
