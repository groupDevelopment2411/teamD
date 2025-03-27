package com.example.demo.Login;

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

	//ログイン画面に遷移
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    //ログイン画面から社員情報登録画面へ遷移
    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, HttpSession session, Model m) {
        Login user = loginService.findByNameAndPassword(name, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/entryForm"; 
        } else {
            m.addAttribute("error", "ユーザー名またはパスワードが間違っています");
            return "login";
        }
    }

    //ログアウト
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	// セッションを無効化
        session.invalidate();
        return "redirect:/login";
    }
}
