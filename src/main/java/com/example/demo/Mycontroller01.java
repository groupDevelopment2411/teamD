package com.example.demo;

import java.util.List;

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
	
	@PostMapping("/Select")
	public String Selct(Model m,
		@RequestParam("id")String id ,
		@RequestParam("pass")String password){
		int numId =Integer.parseInt(id);
		List<Loginentity> Loginentitys = service.selectById(numId);
		if(Loginentitys.isEmpty()) {
			m.addAttribute("errorMessage","IDかパスワードが間違っています。もう一度ご入力ください。");
			return "index";
		}
		
	Loginentity user = Loginentitys.get(0);
	if(user.getPassword().equals(password)) {
		
		return "index2";
	}else {
		m.addAttribute("errorMessage","IDかパスワードが間違っています。もう一度ご入力ください。");
		return "index";
	}
	
	}
	
	
	private Loginservice service;
	
	@RequestMapping("/selectAll")
	public String getAllLoginentitys(Model m) {
		List<Loginentity> Loginentitys =
				service.selectAll();
		m.addAttribute("Loginentity" ,Loginentitys);
		return "kensyudb";
	}
	
}
