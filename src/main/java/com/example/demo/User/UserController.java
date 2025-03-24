package com.example.demo.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@RequestMapping("/idForm")
	public String searchId() {
		return "idForm";
	}
	
	@PostMapping("/selectById")
	public String getById(Model m, @RequestParam("id") String id) {
		int numId = Integer.parseInt(id);
		List<User> users = service.selectById(numId);
		if(users.size() == 0) {
			users = null;
		}
		m.addAttribute("user", users);
		return "id";
	}
}
