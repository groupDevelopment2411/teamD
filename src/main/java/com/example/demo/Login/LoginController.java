package com.example.demo.Login;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//コントローラークラス
@Controller
public class LoginController {
	//インスタンス化
	@Autowired
	private LoginService loginService;

	//login.htmlに遷移
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	//login.htmlからmenu.htmlに遷移
	//ログインユーザの情報をセッションに保持
	@PostMapping("/login")
	public String login(@RequestParam String id, @RequestParam String password, HttpSession session, Model m) {
		List<String> errors = new ArrayList<>();
		int userId;
		try {
			userId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			errors.add("ユーザーIDは数字で入力してください。");
			m.addAttribute("errors", errors);
			return "login";
		}
		//社員名とパスワードを取得
		Login user = loginService.findByNameAndPassword(userId, password);
		//社員情報認証成功の場合
		if (user != null) {
			session.setAttribute("loginUser", user);
			return "redirect:/menu";
			//社員情報認証失敗の場合
		} else {
			if (errors.isEmpty()) {
				errors.add("ユーザー名またはパスワードが間違っています");
			}
			m.addAttribute("errors", errors);
			return "login";
		}
	}

	//ログアウト
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//セッション無効化
		session.invalidate();
		return "redirect:/login";
	}
}
