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
	model.addAttribute("name", entity.getName());  
	model.addAttribute("age", entity.getAge());    
	model.addAttribute("password", entity.getPassword());  
	model.addAttribute("start", entity.getStart()); 
	model.addAttribute("end", entity.getEnd());
	model.addAttribute("entity",entity);
	return "index3";
}
@PostMapping("/update")
	public String update(
	Model  m,
	@RequestParam("id") int id,
	@RequestParam("name") String name,
	@RequestParam("age") String age,
	@RequestParam("password") String password,
	@RequestParam("check") String check,
	@RequestParam(value ="start",required = false) String startS,
	@RequestParam(value ="end",required = false) String endS
	) {
	
	if(name == null || name.trim().isEmpty()) {
		m.addAttribute("ErrorMessage","社員名は必須です");
		m.addAttribute("name" ,"");
		return "index3";
	}
	
	if(age == null || age.trim().isEmpty()) {
		m.addAttribute("ErrorMessage","年齢は必須です");
		m.addAttribute("name", name);
		m.addAttribute("age","");
		return "index3";
	}
	int numberAge = 0;
	try {
		numberAge = Integer.parseInt(age);
	}catch (NumberFormatException e) {
		m.addAttribute("ErrorMessage","年齢は数値で入力してください");
		m.addAttribute("name", name);
		m.addAttribute("age","");
		return "index3";
	}
	Date start = null;
	Date end = null;
	
	if(startS == null || startS.trim().isEmpty()) {
		m.addAttribute("ErrorMessage", "開始日は必須です");
		m.addAttribute("name",name);
		m.addAttribute("age", age);
		return "index3";
	}else {
	try {
		start = Date.valueOf(startS); 
	}catch(IllegalArgumentException e) {
		m.addAttribute("ErrorMessage","開始日の形式が無効です");
		m.addAttribute("name", name);
		m.addAttribute("age", age);
		m.addAttribute("start", "");
		return "index3";
	}
	if(endS == null || endS.trim().isEmpty()) {
		m.addAttribute("ErrorMessage","終了日は必須です");
		m.addAttribute("name",name);
		m.addAttribute("start",start);
		return "index3";
	}else {
	try {
			end = Date.valueOf(endS);
		
	}catch(IllegalArgumentException e) {
		m.addAttribute("ErrorMessage","終了日の形式が無効です");
		m.addAttribute("name", name);
		m.addAttribute("age", age);
		m.addAttribute("start", start);
		m.addAttribute("end", "");
		return "index3";
	}

	if(password == null || password.isEmpty()) {
		m.addAttribute("ErrorMessage" ,"パスワードは必須です");
		m.addAttribute("name", name);
		m.addAttribute("age", age);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("password", "");
		return "index3";}
	
	StringBuilder passErrors = new StringBuilder();
	
	if(!password.matches(".*[A-Z].*")){
		m.addAttribute("ErrorMessage" ,"パスワードは大文字を一つ以上入力が必須です");
		m.addAttribute("name", name);
		m.addAttribute("age", age);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("password", "");
		return "index3";
		
	}else if(password.length()<8) {
		m.addAttribute("ErrorMessage" ,"パスワードは8文字以上で入力してください");
		m.addAttribute("name", name);
		m.addAttribute("age", age);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("password", "");
		return "index3";
	}else if(check == null || check.isEmpty()) {
		m.addAttribute("ErrorMessage" ,"パスワード確認は必須です");
		m.addAttribute("name", name);
		m.addAttribute("age", age);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("password", "");
		m.addAttribute("check","");
		return "index3";
		
	}
	
	Entity entity = new Entity(id,name,numberAge,password,start,end);
	m.addAttribute("entity", entity);
	
	if(password.equals(check)){
		m.addAttribute("entity" ,entity);
		return "index4";
	}else {
		m.addAttribute("ErrorMessage","パスワードが違っています。もう一度ご入力ください。");
		m.addAttribute("name", name);
		m.addAttribute("age", age);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("password", "");
		m.addAttribute("check","");
		return "index3";
	}
}
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
		@RequestParam("age") String age,
		@RequestParam("password") String password,
		@RequestParam("check") String check,
		@RequestParam(value ="start",required = false) Date start,
		@RequestParam(value ="end",required = false) Date end
		) {
	int numberAge = 0;
	if(age == null || age.isEmpty()) {
		m.addAttribute("ErrorMessage","年齢は必須です");
		return "index4";
	}
	
	try {
		numberAge = Integer.parseInt(age);
	}catch (NumberFormatException e) {
		m.addAttribute("ErrorMessage","年齢は数値で入力してください");
		return "index4";
	}
	if(!password.equals(check)) {
		m.addAttribute("errorMessage","パスワードが一致しません。");
		Entity entity = new Entity(id,name,numberAge,password,start,end);
		return "index4";
	}else {
	Entity entity = new Entity (id,name,numberAge,password,start,end);
	service.update(entity);
	m.addAttribute("entity",entity);
	return "index5";
}
}
@PostMapping("/updateForm2")
public String updateForm2(Model model,
		@RequestParam("id") int id,
		@RequestParam("name") String name,
		@RequestParam("age") String age,
		@RequestParam("password") String password,
		@RequestParam("check") String check,
		@RequestParam(value ="start",required = false) Date start,
		@RequestParam(value ="end",required = false) Date end
		){
	int numberAge = Integer.parseInt(age);
	Entity entity = new Entity(id,name,numberAge,password,start,end);
	model.addAttribute("id", id);
	model.addAttribute("name", entity.getName());  
	model.addAttribute("age", entity.getAge());    
	model.addAttribute("password", entity.getPassword());  
	model.addAttribute("start", entity.getStart()); 
	model.addAttribute("end", entity.getEnd());
	model.addAttribute("entity",entity);
	model.addAttribute("check", check);
	return "index3";
}
@PostMapping("/Selectback3")
public String Selectback3() {
	return "index2";
}


@PostMapping("/Kensaku")
public String Kensaku() {
	return "Sample1";
}
}