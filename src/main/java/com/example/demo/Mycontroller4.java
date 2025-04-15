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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
                      @RequestParam("id") String id,
                      RedirectAttributes redirectAttributes) {

    if (id == null || id.trim().isEmpty()) {
        m.addAttribute("ErrorMessage", "idは必須です");
        return "index3.5";
    }

    int numId;
    try {
        numId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
        m.addAttribute("ErrorMessage", "idは数値で入力してください");
        return "index3.5";
    }

    Entity entity = service.getById(numId);
    if (entity == null) {
        m.addAttribute("ErrorMessage", "データが見つかりませんでした");
        return "index3.5";
    }
    redirectAttributes.addAttribute("id", entity.getId());
    redirectAttributes.addAttribute("name", entity.getName());
    redirectAttributes.addAttribute("age", entity.getAge());
    redirectAttributes.addAttribute("password", entity.getPassword());
    redirectAttributes.addAttribute("start", entity.getStart());
    redirectAttributes.addAttribute("end", entity.getEnd());

    return "redirect:/update";
}



@RequestMapping("/update")
	public String update(Model m,
	@RequestParam("id") int id,
	@RequestParam("name") String name,
	@RequestParam("age") int age,
	@RequestParam("password") String password,
	@RequestParam("start") Date start,
	@RequestParam("end") Date end
) {
	
	Entity entity = new Entity(id, name, age, password, start, end);
	m.addAttribute("entity", entity);
	return "index3"; 
}




@PostMapping("/update")
public String update(
    Model model,
    @RequestParam("id") int id,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "age", required = false) String age,
    @RequestParam(value = "password", required = false) String password,
    @RequestParam(value = "check", required = false) String check,
    @RequestParam(value = "start", required = false) String startS,
    @RequestParam(value = "endS", required = false) String endS
) {
   
    if (name == null || name.trim().isEmpty()) {
        model.addAttribute("ErrorMessage", "社員名は必須です");
        model.addAttribute("name", "name");
        model.addAttribute("age", age);
        model.addAttribute("password", password);
        model.addAttribute("check", check);
        model.addAttribute("start", startS);
        model.addAttribute("end", endS);
        return "index3";
    }


    if (age == null || age.trim().isEmpty()) {
        model.addAttribute("ErrorMessage", "年齢は必須です");
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("password", password);
        model.addAttribute("check", check);
        model.addAttribute("start", startS);
        model.addAttribute("end", endS);
        return "index3";
    }

    int numberAge = 0;
    try {
        numberAge = Integer.parseInt(age); // 年齢を数値に変換
    } catch (NumberFormatException e) {
        model.addAttribute("ErrorMessage", "年齢は数値で入力してください");
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("password", password);
        model.addAttribute("check", check);
        model.addAttribute("start", startS);
        model.addAttribute("end", endS);
        return "index3";
    }


    Date start = null;
    Date end = null;

    if (startS == null || startS.trim().isEmpty()) {
        model.addAttribute("ErrorMessage", "開始日は必須です");
        model.addAttribute("name", name);
        model.addAttribute("age", numberAge);
        model.addAttribute("password", password);
        model.addAttribute("check", check);
        model.addAttribute("start", startS);
        model.addAttribute("end", endS);
        return "index3";
    }

    try {
        start = Date.valueOf(startS); // 開始日をDate型に変換
    } catch (IllegalArgumentException e) {
        model.addAttribute("ErrorMessage", "開始日の形式が無効です");
        model.addAttribute("name", name);
        model.addAttribute("age", numberAge);
        model.addAttribute("password", password);
        model.addAttribute("check", check);
        model.addAttribute("start", startS);
        model.addAttribute("end", endS);
        return "index3";
    }


    try {
        end = Date.valueOf(endS); // 終了日をDate型に変換
    } catch (IllegalArgumentException e) {
        model.addAttribute("ErrorMessage", "終了日の形式が無効です");
        model.addAttribute("name", name);
        model.addAttribute("age", numberAge);
        model.addAttribute("start", start);
        model.addAttribute("end", endS);
        return "index3";
    }
    if (password == null || password.isEmpty()) {
        model.addAttribute("ErrorMessage", "パスワードは必須です");
        model.addAttribute("name", name);
        model.addAttribute("age", numberAge);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("password", "");
        return "index3";
    }


    if (check == null || check.isEmpty()) {
        model.addAttribute("ErrorMessage", "パスワード確認は必須です");
        model.addAttribute("name", name);
        model.addAttribute("age", numberAge);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("password", "");
        model.addAttribute("check", "");
        return "index3";
    }


    if (!password.equals(check)) {
        model.addAttribute("ErrorMessage", "パスワード確認が一致しません");
        model.addAttribute("name", name);
        model.addAttribute("age", numberAge);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("password", "");
        model.addAttribute("check", "");
        return "index3";
    }


    Entity entity = new Entity(id, name, numberAge, password, start, end);
    model.addAttribute("entity", entity);
    return "index4"; 
}



@GetMapping("/updateForm")
	public String updateForm() {
    return "index4";
}

@PostMapping("/updateForm")
	public String updateForm(Model model,
          @RequestParam("id") int id,
          @RequestParam("name") String name,
          @RequestParam("age") String age,
          @RequestParam("password") String password,
          @RequestParam("check") String check,
          @RequestParam(value = "start", required = false) Date start,
          @RequestParam(value = "endS", required = false) Date end
) {
   
    int numberAge = 0;
    try {
        numberAge = Integer.parseInt(age);
    } catch (NumberFormatException e) {
        model.addAttribute("ErrorMessage", "年齢は数値で入力してください");
        setEntityAttributes(model, id, name, 0, "", "", start, end);
        return "index4";
    }

    
    if (password == null || password.length() < 8) {
        model.addAttribute("ErrorMessage", "パスワードは8文字以上で入力してください");
        setEntityAttributes(model, id, name, numberAge, "", check, start, end);
        return "index4";
    }

   
    if (!password.equals(check)) {
        model.addAttribute("ErrorMessage", "パスワードが一致しません。");
        setEntityAttributes(model, id, name, numberAge, password, check, start, end);
        return "index4";
    }

    Entity entity = new Entity(id, name, numberAge, password, start, end);
    model.addAttribute("entity", entity);
    return "index5";
}


private void setEntityAttributes(Model model, int id, String name, int age, String password, String check, Date start, Date end) {
    model.addAttribute("id", id);
    model.addAttribute("name", name);
    model.addAttribute("age", age);
    model.addAttribute("password", password);
    model.addAttribute("check", check);
    model.addAttribute("start", start);
    model.addAttribute("end", end);
}




@PostMapping("/Selectback")
	public String Selectback() {
	return "index2";
}

@PostMapping("/Selectback2")
public String Selectbac2k() {
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