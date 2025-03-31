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
	
	//login.htmlに遷移
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    //login.htmlからmenu.htmlに遷移
    //ログイン情報をセッションに保存
    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, HttpSession session, Model m) {
    	//社員名とパスワード取得
        Login user = loginService.findByNameAndPassword(name, password);
        //社員情報認証成功した場合
        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/menu"; 
        //社員情報認証失敗した場合
        } else {
            m.addAttribute("error", "ユーザー名またはパスワードが間違っています");
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
