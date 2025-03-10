package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Mycontroller4 {
	@Autowired DLService service;
	
@GetMapping("/updateForm")
	public String updateForm() {
	return "index3";
}

@PostMapping("/update")
	public String update(
	Model  m,
	@RequestParam("id") int id,
	@RequestParam("name") String name,
	@RequestParam("age") int age,
	@RequestParam("password") String password,
	@RequestParam("start") String start,
	@RequestParam("end") String end
	) {
	
		int numId = id;
		Entity entity = new Entity (id, name, age, password, start, end);
		service.update(entity);
		m.addAttribute("msg","更新が正常に完了しました");
		return "index4";
	}
	
}

