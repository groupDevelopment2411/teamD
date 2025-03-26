package com.example.demo.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Login.Login;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@RequestMapping("/idForm")
	public String searchId() {
		return "idForm";
	}
	
	@PostMapping("/selectByIds")
    public String getByIds(Model m, @RequestParam("ids") String ids, HttpSession session) {
        List<Integer> idList = Arrays.stream(ids.replace("、", ",").split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<User> users = service.selectByIds(idList);
        m.addAttribute("users", users != null ? users : new ArrayList<>());
        // 検索したIDリストをセッションに保存
        session.setAttribute("originalIdList", idList);
        return "id";
    }
	
	@PostMapping("/deleteForm")
	@SuppressWarnings("unchecked")
    public String showDeleteForm(@RequestParam("ids") List<Integer> ids, HttpSession session, Model model) {

        Login loginUser = (Login) session.getAttribute("loginUser");

        if (loginUser != null && ids.contains(loginUser.getId())) {
            model.addAttribute("error", "ログイン中のIDは削除できません");
            // セッションから元のIDリストを取得
            List<Integer> originalIdList = (List<Integer>) session.getAttribute("originalIdList");
            // 元のIDリストでユーザー情報を再取得
            List<User> users = service.selectByIds(originalIdList);
            model.addAttribute("users", users);
            return "id";
        }

        model.addAttribute("ids", ids);
        return "deleteForm";
    }

}
