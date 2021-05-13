package br.com.talthur.librarysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@PostMapping("/login")
	public String home() {
		return "redirect:/home";
	}
//	
	@RequestMapping("/")
	public String start() {
		return "redirect:/home";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}
}
