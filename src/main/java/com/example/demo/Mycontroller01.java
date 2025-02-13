package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Mycontroller01 {
	@RequestMapping("/toppage")
	public String toppage() {
		return "index";
	}
	@PostMapping("/Login")
	public String Login(
		Model m,
		@RequestParam("name") String name,
		@RequestParam("pass") String pass
	
	) {
		m.addAttribute("name" ,name);
		m.addAttribute("pass" ,pass);
		return "Login";
	}
	
	}


