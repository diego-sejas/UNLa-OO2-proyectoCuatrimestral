package com.unla.app.controllers;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unla.app.entities.Users;
import com.unla.app.entities.UsersRole;
import com.unla.app.helpers.AdminSideBarHelper;
import com.unla.app.helpers.MiddlewareHelper;
import com.unla.app.helpers.ConfigHelper;
import com.unla.app.helpers.RouteHelper;
import com.unla.app.services.IUserRoleService;
import com.unla.app.services.IUserService;
import com.unla.app.util.PageRender;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/admin/")
public class UsersController {

	@Autowired
	private IUserService usuarioService;

	@Autowired
	private IUserRoleService userRoleService;

	// GET Listado de Usuarios
	@GetMapping({ "/users", "index", "users.index" })
	public ModelAndView index(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpSession session) {
		ModelAndView view = new ModelAndView(RouteHelper.DASHBOARD_USERS);
		AdminSideBarHelper sideBar = new AdminSideBarHelper();

		String pageName = "Usuarios";
		view.addObject("title", pageName + " - " + ConfigHelper.appName);
		view.addObject("pageName", pageName);
		Users user = (Users) session.getAttribute("USER");
		if (user != null) {
			view.addObject("userName", user.getFirstName() + " " + user.getLastName());
		}
		view.addObject("appName", ConfigHelper.appName);

		view.addObject("sideBarLink", 2); // ID del link para que quede en azul (activo) en el menu izquierdo
		view.addObject("sideBar", sideBar.lst_adminSideBar);

		// Paginacion
		Pageable pageRequest = PageRequest.of(page, ConfigHelper.listResultadosMax); // cantidad de registros por
																						// pagina.
		Page<Users> users = usuarioService.findAll(pageRequest);
		PageRender<Users> pageRender = new PageRender<>("users", users);

		model.addAttribute("users", users);
		List<UsersRole> roles = userRoleService.findAll();
		model.addAttribute("roles", roles);

		model.addAttribute("page", pageRender);

		MiddlewareHelper mHelper = new MiddlewareHelper(session);
		return mHelper.AuthMiddleware(mHelper.RoleMiddleware(view, 25, roles));
	}

	// GET Crear Usuario Nuevo
	@GetMapping({ "users/create", "create", "users.create" })
	public ModelAndView create(Model model, HttpSession session) {
		ModelAndView view = new ModelAndView(RouteHelper.DASHBOARD_NEW_USERS);
		AdminSideBarHelper sideBar = new AdminSideBarHelper();

		String pageName = "Nuevo Usuario";
		view.addObject("title", pageName + " - " + ConfigHelper.appName);
		view.addObject("pageName", pageName);
		Users loggedUser = (Users) session.getAttribute("USER");
		if (loggedUser != null) {
			view.addObject("userName", loggedUser.getFirstName() + " " + loggedUser.getLastName());
		}
		view.addObject("appName", ConfigHelper.appName);

		view.addObject("sideBarLink", 2); // ID del link para que quede en azul (activo) en el menu izquierdo
		view.addObject("sideBar", sideBar.lst_adminSideBar);

		Users user = new Users();
		model.addAttribute("user", user);
		// Listado de Roles Disponibles

		List<UsersRole> roles = userRoleService.findAll();
		model.addAttribute("roles", roles);

		MiddlewareHelper mHelper = new MiddlewareHelper(session);
		return mHelper.AuthMiddleware(mHelper.RoleMiddleware(view, 50, roles));
	}

	// POST Guardar Usuario Nuevo
	@PostMapping({ "/users", "store", "users.store" })
	public String store(@Valid Users user, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			flash.addFlashAttribute("error", result.getAllErrors());
			return "redirect:/admin/users/create";
		}
		user.setActivo(true);

		String passwordHash;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(user.getPassword().getBytes());
			byte[] digest = md.digest();
			passwordHash = DatatypeConverter
			.printHexBinary(digest).toUpperCase();
		}catch(Exception e){
			flash.addFlashAttribute("message", e.getMessage());
			return "redirect:/admin/users";
		}
		user.setPassword(passwordHash);

		usuarioService.save(user);

		flash.addFlashAttribute("success", "Cliente creado con exito!");
		return "redirect:/admin/users";
	}

	// GET Editar Usuario
	@GetMapping({ "/users/edit/{id}", "edit", "users.edit" })
	public ModelAndView edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash,
			HttpSession session) {
		Users user = null;
		ModelAndView view = new ModelAndView(RouteHelper.DASHBOARD_EDIT_USERS);
		AdminSideBarHelper sideBar = new AdminSideBarHelper();

		if (id > 0) {
			user = usuarioService.findOne(id);
			if (user == null) {
				flash.addFlashAttribute("error", "El ID del Usuario no existe en la Base de Datos");
				ModelAndView modelAndView = new ModelAndView("redirect:/admin/users");
				return modelAndView;
			}
		}

		String pageName = "Editar Usuario - #" + user.getId() + " " + user.getFirstName() + " " + user.getLastName();
		view.addObject("title", pageName + " - " + ConfigHelper.appName);
		view.addObject("pageName", pageName);
		Users loggedUser = (Users) session.getAttribute("USER");
		if (loggedUser != null) {
			view.addObject("userName", loggedUser.getFirstName() + " " + loggedUser.getLastName());
		}
		view.addObject("appName", ConfigHelper.appName);
		view.addObject("sideBarLink", 2); // ID del link para que quede en azul (activo) en el menu izquierdo
		view.addObject("sideBar", sideBar.lst_adminSideBar);

		// Listado de Roles Disponibles

		List<UsersRole> roles = userRoleService.findAll();
		view.addObject("roles", roles);

		model.put("user", user);

		model.put("titulo", "Editar Usuario");

		MiddlewareHelper mHelper = new MiddlewareHelper(session);
		return mHelper.AuthMiddleware(mHelper.RoleMiddleware(view, 50, roles));
	}

	// POST Editar Usuario / Guardar edición
	@PostMapping({ "/users/{id}", "update", "users.update" })
	public String update(@Valid Users user, @PathVariable(value = "id") Long id, BindingResult result, Model model,
			RedirectAttributes flash, HttpSession session) {

		if (result.hasErrors()) {
			flash.addFlashAttribute("error", result.getAllErrors());
			return "redirect:/admin/users/edit";
		}

		if (id > 0) {
			Users checkID = usuarioService.findOne(id);
			if (checkID == null) {
				flash.addFlashAttribute("error", "El ID del Usuario no existe en la Base de Datos");
				return "redirect:/admin/users";
			}
		}

		String passwordHash;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(user.getPassword().getBytes());
			byte[] digest = md.digest();
			passwordHash = DatatypeConverter
			.printHexBinary(digest).toUpperCase();
		}catch(Exception e){
			flash.addFlashAttribute("message", e.getMessage());
			return "redirect:/admin/users";
		}
		user.setPassword(passwordHash);
		
		usuarioService.save(user);

		// si es el mismo usuario que hace los cambios actualizamos la sesion
		Users sessionUser = (Users) session.getAttribute("USER");
		if (sessionUser.getId().equals(id)) {
			session.setAttribute("USER", user);
		}

		flash.addFlashAttribute("success", "Usuario Editado con exito!");
		return "redirect:/admin/users";
	}

	// DELETE Eliminar Usuario
	@DeleteMapping({ "/users/{id}", "destroy", "users.destroy" })
	public String destroy(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			usuarioService.delete(id);
			flash.addFlashAttribute("success", "Usuario eliminado con exito!");
		}
		return "redirect:/admin/users";
	}

}
