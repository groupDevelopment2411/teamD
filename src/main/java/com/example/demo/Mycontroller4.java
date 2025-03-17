package com.example.demo;

import java.sql.Date;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Mycontroller4 {
	@Autowired DLService service;
	
	@Autowired HttpSession session;
	
@GetMapping("/updateForm")
	public String updateForm(Model model) {
	Integer id =(Integer) session.getAttribute("id");
	Entity entity = service.getById(id);
	if(entity == null) {
		model.addAttribute("errorMessage","データが見つかりませんでした");
		return "index2";
	}
	model.addAttribute("entity",entity);
	return "index3";
}



@PostMapping("/update")
	public String update(
	Model  m,
	@RequestParam("id") int id,
	@RequestParam("name") String name,
	@RequestParam("age") Integer age,
	@RequestParam("password") String password,
	@RequestParam("check") String check,
	@RequestParam(value ="start",required = false) Date start,
	@RequestParam(value ="end",required = false) Date end
	) {
	if(name.isEmpty()) {
		m.addAttribute("errorMessage","社員名は必須です");
		return "index3";
	}
	if(age == null) {
		m.addAttribute("errorMessage","年齢は必須です");
		return "index3";
	}
	if(start == null) {
		m.addAttribute("errorMessage","開始日は必須です");
		return "index3";
	}
	if(end == null) {
		m.addAttribute("errorMessage","終了日は必須です");
		return "index3";
	}
	if(password.isEmpty()) {
		m.addAttribute("errorMessage" ,"パスワードは必須です");
		return "index3";
		
	}else if(!password.matches(".*[A-Z].*")){
		m.addAttribute("errorMessage" ,"パスワードは大文字を一つ以上入力が必須です");
		return "index3";
	}else if(password.length()<8) {
		m.addAttribute("errorMessage" ,"パスワードは8文字以上で入力してください");
		return "index3";
	}
	if(check.isEmpty()) {
		m.addAttribute("errorMessage" ,"パスワード確認は必須です");
		return "index3";
	}
	
	if(password.equals(check)){
		int numId = id;
		Entity entity = new Entity (id, name, age, password,  start, end);
		service.update(entity);
		return "index4";
	}else {
		m.addAttribute("errorMessage","パスワードが違っています。もう一度ご入力ください。");
		return "index3";
	}

	}
	
}

