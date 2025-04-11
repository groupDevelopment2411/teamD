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

	//idForm.htmlに遷移
	@RequestMapping("/idForm")
	public String searchId() {
		return "idForm";
	}

	//idForm.htmlからid.htmlに遷移
	//社員ID検索して存在すれば社員IDを表示
	@PostMapping("/selectByIds")
	public String getByIds(Model m, @RequestParam("ids") String ids, HttpSession session) {
		// エラーメッセージを格納
		List<String> errors = new ArrayList<>();
		// 何も入力されなかった場合
		if (ids == null || ids.trim().isEmpty()) {
			errors.add("社員IDを入力してください");
			m.addAttribute("errors", errors);
			return "idForm";
		}
		// 入力された社員ID文字列をカンマや全角カンマで分割し、空白を取り除き、Integer型のリストに変換
		List<Integer> idList = Arrays.stream(ids.replace("、", ",").split(","))
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		// 社員IDから社員情報を検索
		List<User> users = service.selectByIds(idList);
		// セッションからログインユーザ情報を取得
		Login loginUser = (Login) session.getAttribute("loginUser");
		// 社員IDが存在しない場合
		if (users.isEmpty()) {
			errors.add("該当する社員IDがありません");
		}
		// 検索対象がセッションに保存されているログイン中のユーザの場合
		List<Integer> userIds = users.stream().map(User::getId).collect(Collectors.toList());
		if (loginUser != null && userIds.contains(loginUser.getId())) {
			errors.add("ログイン中のIDが含まれています");
		}
		// エラーがある場合
		if (!errors.isEmpty()) {
			m.addAttribute("errors", errors);
			return "idForm";
		}

		// エラーがない場合、社員IDをセッションに格納して遷移
		m.addAttribute("users", users != null ? users : new ArrayList<>());
		session.setAttribute("originalIdList", idList);
		//セッションに入力したIDを保存
		session.setAttribute("inputIds", ids);
		session.setAttribute("previousUrl", "/idForm");
		//セッションにidForm.htmlで検索したID保存
		session.setAttribute("allUsers", users);
		return "id"; 
	}
	//id.htmlからidForm.htmlに戻る
	@PostMapping("/backToIdForm")
	public String backToIdForm(HttpSession session, Model model) {
	    String inputIds = (String) session.getAttribute("inputIds");
	    model.addAttribute("ids", inputIds);
	    return "idForm";
	}

	//id.htmlからdeleteFormConfirm.htmlに遷移
	//削除したい社員IDを選択
	@PostMapping("/deleteFormConfirm")
		public String showDeleteForm(@RequestParam("ids") List<Integer> ids, Model m, HttpSession session) {
			//戻るボタンを押した時の再表示用
			List<User> users = service.selectByIds(ids);
			m.addAttribute("ids", ids);
			//戻るボタンを押した時用のURL保存
			session.setAttribute("previousUrl", "/id.html"); 
			//戻るボタンを押した時の再表示用の保持
			session.setAttribute("selectedUsers", users);  
			return "deleteFormConfirm";
		}

}
