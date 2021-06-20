package com.unla.app.controllers;

import com.unla.app.entities.Users;
import com.unla.app.helpers.ConfigHelper;
import com.unla.app.helpers.RouteHelper;
import com.unla.app.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.MessageDigest;

//Session
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private IUserService usuarioService;

	@GetMapping({ "/login", "login", "login" })
	public ModelAndView login(HttpSession session) {
		ModelAndView view = new ModelAndView(RouteHelper.LOGIN);
		Users user = (Users) session.getAttribute("USER");		
		if (user != null) {
			return new ModelAndView("redirect:/");
		}
		view.addObject("title", "Iniciar Sesión - " + ConfigHelper.appName);
		return view;
	}

	@PostMapping("/login")
	public String login_post(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, RedirectAttributes flash) {
		Users user = null;
		user = usuarioService.findOneByEmail(email);
		
		String passwordHash;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			passwordHash = DatatypeConverter
			.printHexBinary(digest).toUpperCase();
		}catch(Exception e){
			flash.addFlashAttribute("message", e.getMessage());
			return "redirect:login";
		}

		// Si el usuario no existe
		if (user == null) {
			flash.addFlashAttribute("message", "El usuario no se encuentra registrado.");
			return "redirect:login";
		}
		// Si la contraseña es incorrecta
		if (!passwordHash.equals(user.getPassword())) {
			flash.addFlashAttribute("message", "Contraseña Incorrecta.");
			return "redirect:login";
		}
		// Todo ok, guardamos la session
		session.setAttribute("USER", user);
		return "redirect:/";
	}

	@PostMapping("/logout")
	public String logout_post(HttpSession session, RedirectAttributes flash) {
		session.removeAttribute("USER"); // logout
		flash.addFlashAttribute("message", "Saliste del Sistema Correctamente. Volver a Iniciar Sesión.");
		return "redirect:login";
	}
}
