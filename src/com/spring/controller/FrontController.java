package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

	@GetMapping("/")
	public String goHome(Model model){
		model.addAttribute("name", "James");
		return "login";
	}
}
