package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mycontroller2 {
	@GetMapping("/Select")
	public String Select(Model model) {
		LocalDateTime currentTime = LocalDateTime.now();
		model.addAttribute("currentTime",currentTime);
		 return"index2";
	}

}
