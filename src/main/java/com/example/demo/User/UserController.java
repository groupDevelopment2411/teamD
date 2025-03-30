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

//コントローラークラス
@Controller
public class UserController {
	//インスタンス化
	@Autowired
	private UserService service;

	//社員ID検索画面へ遷移
	@RequestMapping("/idForm")
	public String searchId() {
		return "idForm";
	}

	//社員ID検索
	@PostMapping("/selectByIds")
	public String getByIds(Model m, @RequestParam("ids") String ids, HttpSession session) {
		//受け取った社員ID情報の変換処理
		List<Integer> idList = Arrays.stream(ids.replace("、", ",").split(","))
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		List<User> users = service.selectByIds(idList);
		m.addAttribute("users", users != null ? users : new ArrayList<>());
		session.setAttribute("originalIdList", idList);
		
		session.setAttribute("previousUrl", "/idForm");
		return "id";
	}

	//社員ID表示画面から社員情報画面へ遷移
	@PostMapping("/deleteFormConfirm")
	@SuppressWarnings("unchecked")
    public String showDeleteForm(@RequestParam("ids") List<Integer> ids, HttpSession session, Model m) {

        Login loginUser = (Login) session.getAttribute("loginUser");
        //ログイン中のユーザの社員IDが含まれているか確認
        if (loginUser != null && ids.contains(loginUser.getId())) {
        	//エラーメッセージ
            m.addAttribute("error", "ログイン中のIDは削除できません");
            // 検索した社員IDリストを取得
            List<Integer> originalIdList = (List<Integer>) session.getAttribute("originalIdList");
            // 社員IDリストでユーザー情報を再取得
            List<User> users = service.selectByIds(originalIdList);
            m.addAttribute("users", users);
            return "id";
        }
        m.addAttribute("ids", ids);
        return "deleteFormConfirm";
    }

}
