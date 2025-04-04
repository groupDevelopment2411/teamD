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
	String userName = (String) session.getAttribute("userName");
	Entity entity = service.getById(id);
	if(entity == null) {
		model.addAttribute("errorMessage","データが見つかりませんでした");
		return "index2";
	}
	model.addAttribute("userName",userName);
	model.addAttribute("id", id);
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
	Entity entity = new Entity(id,name,age,password,start,end);
	m.addAttribute("entity", entity);
	
	if(name.isEmpty()) {
		m.addAttribute("nameErrorMessage","社員名は必須です");
		return "index3";
	}
	if(age == null) {
		m.addAttribute("ageErrorMessage","年齢は必須です");
		return "index3";
	}
	if(start == null) {
		m.addAttribute("startErrorMessage","開始日は必須です");
		return "index3";
	}
	if(end == null) {
		m.addAttribute("endErrorMessage","終了日は必須です");
		return "index3";
	}
	if(password == null || password.isEmpty()) {
		m.addAttribute("passErrorMessage" ,"パスワードは必須です");
		return "index3";}
	
	StringBuilder passErrors = new StringBuilder();
	
	if(!password.matches(".*[A-Z].*")){
		m.addAttribute("passErrorMessage" ,"パスワードは大文字を一つ以上入力が必須です");
		return "index3";
		
	}else if(password.length()<8) {
		m.addAttribute("passErrorMessage" ,"パスワードは8文字以上で入力してください");
		return "index3";
	}else if(check == null || check.isEmpty()) {
		m.addAttribute("checkErrorMessage" ,"パスワード確認は必須です");
		return "index3";
		
	}
	
	if(password.equals(check)){
		m.addAttribute("entity" ,entity);
		return "index4";
	}else {
		m.addAttribute("checkErrorMessage","パスワードが違っています。もう一度ご入力ください。");
		return "index3";
	}
}

@PostMapping("/Selectback")
public String Selectback() {
	return "index2";
}


@PostMapping("/updatecomplete")
public String updatecomplete(Model  m,
		@RequestParam("id") int id,
		@RequestParam("name") String name,
		@RequestParam("age") Integer age,
		@RequestParam("password") String password,
		@RequestParam("check") String check,
		@RequestParam(value ="start",required = false) Date start,
		@RequestParam(value ="end",required = false) Date end
		) {
	if(!password.equals(check)) {
		m.addAttribute("errorMessage","パスワードが一致しません。");
		Entity entity = new Entity(id,name,age,password,start,end);
		return "index4";
	}else {
	Entity entity = new Entity (id, name, age, password, start, end);
	service.update(entity);
	m.addAttribute("entity",entity);
	return "index5";
}
}
@PostMapping("/updateForm2")
public String updateForm2(Model model,
		@RequestParam("id") int id,
		@RequestParam("name") String name,
		@RequestParam("age") Integer age,
		@RequestParam("password") String password,
		@RequestParam("check") String check,
		@RequestParam(value ="start",required = false) Date start,
		@RequestParam(value ="end",required = false) Date end
		){
	Entity entity = new Entity(id,name,age,password,start,end);
	model.addAttribute("entity",entity);
	model.addAttribute("check", check);
	return "index3";
}
@PostMapping("/Selectback3")
public String Selectback3() {
	return "index2";
}
}

