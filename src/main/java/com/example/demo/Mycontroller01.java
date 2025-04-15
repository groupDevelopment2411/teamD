package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 

@Controller
public class Mycontroller01 {
	@Autowired
	private Loginservice service;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/toppage")
	public String toppage() {
		return "index";
	}
	
	@PostMapping("/Select")
	public String Select(Model m,
		@RequestParam("id")String id ,
		@RequestParam("pass")String password){
		
		int numId;
		
		try {
			numId =Integer.parseInt(id);
		}catch (NumberFormatException e) {
			m.addAttribute("errorMessage","idは数値で入力してください");
			m.addAttribute("id","");
			return "index";
		}
		
		List<Loginentity> Loginentitys = service.selectById(numId);
		
		if(Loginentitys.isEmpty()) {
			m.addAttribute("errorMessage","IDかパスワードが間違っています。もう一度ご入力ください。");
			return "index";
		}
		
	Loginentity user = Loginentitys.get(0);
	if(user.getPassword().equals(password)) {
		String userName = user.getName();
		m.addAttribute("userName",user.getName());
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		m.addAttribute("currentTime",formattedTime);
			this.session.setAttribute("id",user.getId());
			this.session.setAttribute("userName",userName);
			this.session.setAttribute("currentTime" , formattedTime);
		return "index2";
	}else {
		m.addAttribute("errorMessage","IDかパスワードが間違っています。もう一度ご入力ください。");
		return "index";
	}
	}
	
	}

