package com.example.demo.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@PostMapping("/selectByIds")
	public String getByIds(Model m, @RequestParam("ids") String ids) {
		List<Integer> idList = Arrays.stream(ids.replace("、", ",").split(","))
                .map(String::trim) // 空白を除去
                .map(Integer::parseInt) // 数値に変換
                .collect(Collectors.toList());
		List<User> users = service.selectByIds(idList);
		m.addAttribute("users", users != null ? users : new ArrayList<>());
		return "id";
	}
	
	@PostMapping("/deleteForm")
	public String showDeleteForm(@RequestParam("ids") List<Integer> ids, Model model) {
	    model.addAttribute("ids", ids); // 選択したIDリストを渡す
	    return "deleteForm";
	}

}
