package com.example.demo;

import java.sql.Date;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
	public class Mycontroller4 {
	@Autowired DLService service;
	
	@Autowired HttpSession session;
	
@GetMapping("/IDcheck")
    public String IDcheck() {
    return "index3.5";

    			
  }


@PostMapping("/IDcheck")
public String IDcheck(Model m,
	@RequestParam("id")String id
) {
	
	
	if(id == null || id.trim().isEmpty()) {
		m.addAttribute("ErrorMessage","idは必須です");
		return "index3.5";
	}
	int numId;
	
	try {
		numId =Integer.parseInt(id);
	}catch (NumberFormatException e) {
		m.addAttribute("ErrorMessage","idは数値で入力してください");
		m.addAttribute("id","id");
		return "index3.5";
	}
	
	Entity entity = service.getById(numId);
	m.addAttribute("entity", entity);
	if(entity == null) {
		m.addAttribute("ErrorMessage","データが見つかりませんでした");
		return "index3.5";
	}else {
		this.session.setAttribute("id", entity.getId());
		this.session.setAttribute("name", entity.getName());
		this.session.setAttribute("age", entity.getAge());
		this.session.setAttribute("password", entity.getPassword());
		this.session.setAttribute("start", entity.getStart());
		this.session.setAttribute("end", entity.getEnd());
		this.session.setAttribute("entity",entity);
		return "redirect:/update";
	}
}

@PostMapping("/Selectback2")
public String Selectbac2k() {
	return "index2";
}


@RequestMapping("/update")
	public String update(Model m) {
    
    m.addAttribute("id", this.session.getAttribute("id"));
    m.addAttribute("name", this.session.getAttribute("name"));
    m.addAttribute("age", this.session.getAttribute("age"));
    m.addAttribute("password", this.session.getAttribute("password"));
    m.addAttribute("start", this.session.getAttribute("start"));
    m.addAttribute("end", this.session.getAttribute("end"));
    m.addAttribute("entity", this.session.getAttribute("entity"));

    return "index3"; 
}




@PostMapping("/updateForm")
	public String updateForm(
			Model  model,
			@RequestParam("id") int id,
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "age", required = false) String age,
			@RequestParam(value ="password" ,required = false) String password,
			@RequestParam(value="check",required = false) String check,
			@RequestParam(value ="startS",required = false) String startS,
			@RequestParam(value = "endS", required = false) String endS
			) {

	if(name == null || name.trim().isEmpty()) {
	model.addAttribute("ErrorMessage","社員名は必須です");
    model.addAttribute("name", name);
    model.addAttribute("age", this.session.getAttribute("age"));
    model.addAttribute("password", this.session.getAttribute("password"));
    model.addAttribute("start", this.session.getAttribute("start"));
    model.addAttribute("end", this.session.getAttribute("end"));
	return "index3";
	}

	if(age == null || age.trim().isEmpty()) {
	model.addAttribute("ErrorMessage","年齢は必須です");
    model.addAttribute("name", name);
    model.addAttribute("age", age); 
    model.addAttribute("password", this.session.getAttribute("password"));
    model.addAttribute("check", check);
    model.addAttribute("start", this.session.getAttribute("start"));
    model.addAttribute("end", this.session.getAttribute("end"));
	return "index3";
	}
	int numberAge = 0;
	try {
		numberAge = Integer.parseInt(age);
	}catch (NumberFormatException e) {
    model.addAttribute("ErrorMessage", "年齢は数値で入力してください");
    model.addAttribute("name", name);
    model.addAttribute("age", age); 
    model.addAttribute("password", this.session.getAttribute("password"));
    model.addAttribute("check", check);
    model.addAttribute("start", this.session.getAttribute("start"));
    model.addAttribute("end", this.session.getAttribute("end"));
    return "index3";
	}

	Date start = null;
	Date end = null;

if(startS == null || startS.trim().isEmpty()) {
	model.addAttribute("ErrorMessage", "開始日は必須です");
	  model.addAttribute("name", name);
	  model.addAttribute("age", numberAge); 
	  model.addAttribute("password", password);
	  model.addAttribute("check", check);
	  model.addAttribute("start", start);
	  model.addAttribute("end", end);
	return "index3";
}
try {
	start = Date.valueOf(startS); 
} catch(IllegalArgumentException e) {
	model.addAttribute("ErrorMessage","開始日の形式が無効です");
	 model.addAttribute("name", name);
	 model.addAttribute("age", numberAge); 
	 model.addAttribute("password", password);
	 model.addAttribute("check", check);
	 model.addAttribute("start", start);
	 model.addAttribute("end", end);
	return "index3";
}

try {
	end = Date.valueOf(endS);
} catch(IllegalArgumentException e) {
	model.addAttribute("ErrorMessage","終了日の形式が無効です");
	model.addAttribute("name", name);
	model.addAttribute("age", numberAge);
	model.addAttribute("start", start);
	model.addAttribute("end", "");
	return "index3";
}



if(password == null || password.isEmpty()) {
	model.addAttribute("ErrorMessage" ,"パスワードは必須です");
	model.addAttribute("name", name);
	model.addAttribute("age", numberAge);
	model.addAttribute("start", start);
	model.addAttribute("end", end);
	model.addAttribute("password", "");
	return "index3";}


if(!password.matches(".*[A-Z].*")){
	model.addAttribute("ErrorMessage" ,"パスワードは大文字を一つ以上入力が必須です");
	model.addAttribute("name", name);
	model.addAttribute("age", numberAge);
	model.addAttribute("start", start);
	model.addAttribute("end", end);
	model.addAttribute("password", "");
	return "index3";
	
}else if(password.length()<8) {
	model.addAttribute("ErrorMessage" ,"パスワードは8文字以上で入力してください");
	model.addAttribute("name", name);
	model.addAttribute("age", numberAge);
	model.addAttribute("start", start);
	model.addAttribute("end", end);
	model.addAttribute("password", "");
	return "index3";
}else if(check == null || check.isEmpty()) {
	model.addAttribute("ErrorMessage" ,"パスワード確認は必須です");
	model.addAttribute("name", name);
	model.addAttribute("age", numberAge);
	model.addAttribute("start", start);
	model.addAttribute("end", end);
	model.addAttribute("password", "");
	model.addAttribute("check","");
	return "index3";
	
}

Entity entity = new Entity(id,name,numberAge,password,start,end);
model.addAttribute("entity", entity);

if(password.equals(check)){
	model.addAttribute("entity" ,entity);
	model.addAttribute("start", start); 
	model.addAttribute("end", end); 
	
    this.session.setAttribute("id", id);
    this.session.setAttribute("name", name);
    this.session.setAttribute("age", numberAge);
    this.session.setAttribute("password", password);
    this.session.setAttribute("start", start);
    this.session.setAttribute("end", end);
    this.session.setAttribute("check", check);
	return "index4";
}else {
	model.addAttribute("ErrorMessage","パスワードが違っています。もう一度ご入力ください。");
	model.addAttribute("name", name);
	model.addAttribute("age", age);
	model.addAttribute("start", start);
	model.addAttribute("end", end);
	model.addAttribute("password", "");
	model.addAttribute("check","");
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
	@RequestParam("age") String age,
	@RequestParam("password") String password,
	@RequestParam("check") String check,
	@RequestParam(value ="startS",required = false) Date start,
	@RequestParam (value = "endS", required = false) Date end
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
	m.addAttribute("ErrorMessage","パスワードが一致しません。");
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