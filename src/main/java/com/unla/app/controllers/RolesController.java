package com.unla.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.unla.app.entities.Users;
import com.unla.app.entities.UsersRole;
import com.unla.app.helpers.AdminSideBarHelper;
import com.unla.app.helpers.MiddlewareHelper;
import com.unla.app.helpers.ConfigHelper;
import com.unla.app.helpers.RouteHelper;
import com.unla.app.services.IUserRoleService;
import com.unla.app.util.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RolesController {

	@Autowired
	private IUserRoleService userRoleService;


	// GET listado de Roles
	@GetMapping({ "/admin/roles", "roles", "roles" })
	public ModelAndView roles(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpSession session) {
		ModelAndView view = new ModelAndView(RouteHelper.DASHBOARD_ROLES);
		AdminSideBarHelper sideBar = new AdminSideBarHelper();

		String pageName = "Roles";
		view.addObject("title", pageName + " - " + ConfigHelper.appName);
		view.addObject("pageName", pageName);
		Users user = (Users) session.getAttribute("USER");
		if (user != null) {
			view.addObject("userName", user.getFirstName() + " " + user.getLastName());
		}
		view.addObject("appName", ConfigHelper.appName);

		view.addObject("sideBarLink", 3); // ID del link para que quede en azul (activo) en el menu izquierdo
		view.addObject("sideBar", sideBar.lst_adminSideBar);

		// Paginacion
		Pageable pageRequest = PageRequest.of(page, ConfigHelper.listResultadosMax); // cantidad de registros por
																						// pagina.
		Page<UsersRole> usersRole = userRoleService.findAll(pageRequest);
		PageRender<UsersRole> pageRender = new PageRender<>("usersRole", usersRole);
		model.addAttribute("usersRole", usersRole);
		model.addAttribute("page", pageRender);

		List<UsersRole> roles = userRoleService.findAll();
		MiddlewareHelper mHelper = new MiddlewareHelper(session);
		return mHelper.AuthMiddleware(mHelper.RoleMiddleware(view, 25, roles));
	}

	// GET Crear Role Nuevo
	@GetMapping({ "/admin/roles/create", "create", "roles.create" })
	public ModelAndView create(Model model, HttpSession session) {
		ModelAndView view = new ModelAndView(RouteHelper.DASHBOARD_NEW_ROLES);
		AdminSideBarHelper sideBar = new AdminSideBarHelper();

		String pageName = "Nuevo Rol";
		view.addObject("title", pageName + " - " + ConfigHelper.appName);
		view.addObject("pageName", pageName);
		Users user = (Users) session.getAttribute("USER");
		if (user != null) {
			view.addObject("userName", user.getFirstName() + " " + user.getLastName());
		}
		view.addObject("appName", ConfigHelper.appName);

		view.addObject("sideBarLink", 2); // ID del link para que quede en azul (activo) en el menu izquierdo
		view.addObject("sideBar", sideBar.lst_adminSideBar);

		UsersRole userRole = new UsersRole();
		model.addAttribute("role", userRole);

		List<UsersRole> roles = userRoleService.findAll();
		MiddlewareHelper mHelper = new MiddlewareHelper(session);
		return mHelper.AuthMiddleware(mHelper.RoleMiddleware(view, 50, roles));
	}

	// POST Guardar Role Nuevo
	@PostMapping({ "/admin/roles", "store", "roles.store" })
	public String store(@Valid UsersRole user, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("error", result.getAllErrors());
			return "redirect:/admin/roles/create";
		}

		userRoleService.save(user);

		flash.addFlashAttribute("success", "Rol creado con exito!");
		return "redirect:/admin/roles";
	}

	// GET Editar Role
	@GetMapping({ "/admin/roles/edit/{id}", "edit", "roles.edit" })
	public ModelAndView edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash,
			HttpSession session) {
		UsersRole role = null;
		ModelAndView view = new ModelAndView(RouteHelper.DASHBOARD_EDIT_ROLES);
		AdminSideBarHelper sideBar = new AdminSideBarHelper();

		if (id > 0) {
			role = userRoleService.findOne(id);
			if (role == null) {
				flash.addFlashAttribute("error", "El ID del Rol no existe en la Base de Datos");
				ModelAndView modelAndView = new ModelAndView("redirect:/admin/roles");
				return modelAndView;
			}
		}

		String pageName = "Editar Rol - #" + role.getName();
		view.addObject("title", pageName + " - " + ConfigHelper.appName);
		view.addObject("pageName", pageName);
		Users loggedUser = (Users) session.getAttribute("USER");
		if (loggedUser != null) {
			view.addObject("userName", loggedUser.getFirstName() + " " + loggedUser.getLastName());
		}
		view.addObject("appName", ConfigHelper.appName);
		view.addObject("sideBarLink", 2); // ID del link para que quede en azul (activo) en el menu izquierdo
		view.addObject("sideBar", sideBar.lst_adminSideBar);

		model.put("role", role);
		model.put("titulo", "Editar Rol");

		List<UsersRole> roles = userRoleService.findAll();
		MiddlewareHelper mHelper = new MiddlewareHelper(session);
		return mHelper.AuthMiddleware(mHelper.RoleMiddleware(view, 50, roles));
	}

	// POST Editar Role / Guardar Edición
	@PostMapping({ "/admin/roles/{id}", "update", "roles.update" })
	public String update(@Valid UsersRole user, @PathVariable(value = "id") Long id, BindingResult result, Model model,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("error", result.getAllErrors());
			return "redirect:/admin/roles/edit";
		}

		if (id > 0) {
			UsersRole checkID = userRoleService.findOne(id);
			if (checkID == null) {
				flash.addFlashAttribute("error", "El ID del Rol no existe en la Base de Datos");
				return "redirect:/admin/roles";
			}
		}

		userRoleService.save(user);
		flash.addFlashAttribute("success", "Rol Editado con exito!");
		return "redirect:/admin/roles";
	}

	// DELETE Eliminar Role
	@DeleteMapping({ "/admin/roles/{id}", "destroy", "roles.destroy" })
	public String destroy(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			userRoleService.delete(id);
			flash.addFlashAttribute("success", "Rol eliminado con exito!");
		}
		return "redirect:/admin/roles";
	}
}
