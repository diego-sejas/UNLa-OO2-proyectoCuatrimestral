package com.unla.app.controllers;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.app.entities.Users;
import com.unla.app.entities.UsersRole;
import com.unla.app.helpers.AdminSideBarHelper;
import com.unla.app.helpers.MiddlewareHelper;
import com.unla.app.helpers.ConfigHelper;
import com.unla.app.helpers.RouteHelper;
import com.unla.app.services.IUserRoleService;

@Controller
@RequestMapping("/")
public class AdminController {

	@Autowired
	private IUserRoleService userRoleService;
	
	@GetMapping({ "/admin", "admin", "admin.index" })
	public String admin(HttpSession session) {
		return "redirect:/admin/dashboard";	
	}

	@GetMapping({ "/admin/dashboard", "index", "dashboard" })
	public ModelAndView index(HttpSession session) {
		ModelAndView view = new ModelAndView(RouteHelper.DASHBOARD);
		AdminSideBarHelper sideBar = new AdminSideBarHelper();

		String pageName = "Panel de Administración";
		view.addObject("title", pageName + " - " + ConfigHelper.appName);
		view.addObject("pageName", pageName);
		Users user = (Users) session.getAttribute("USER");
		if(user != null){
			view.addObject("userName", user.getFirstName() + " " + user.getLastName());
		}	
		view.addObject("appName", ConfigHelper.appName);

		view.addObject("sideBarLink", 1); // ID del link
		view.addObject("sideBar", sideBar.lst_adminSideBar);

		List<UsersRole> roles = userRoleService.findAll();		
	
		MiddlewareHelper mHelper = new MiddlewareHelper(session);
		return mHelper.AuthMiddleware(mHelper.RoleMiddleware(view, 25, roles));
	}

}
