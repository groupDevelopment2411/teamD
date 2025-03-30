package com.example.demo.Delete;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Login.Login;
import com.example.demo.User.User;
import com.example.demo.User.UserService;

//コントローラークラス
@Controller
public class DeleteController {
	//インスタンス化
	@Autowired
	private DeleteService service;
	@Autowired
	private UserService userService;
	
	//社員情報削除(入力)画面へ遷移
	@RequestMapping("/deleteForm")
	public String deleteForm(HttpSession session, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		if (referer != null) {
			session.setAttribute("previousUrl", referer);
		}
		return "deleteForm";
	}
	
	//社員情報削除(入力)画面から社員情報削除(確認)画面へ遷移
	@PostMapping("/deleteSearch")
	public String deleteSearch(Model m, @RequestParam("ids") String ids, HttpSession session) {
	    List<Integer> idList = Arrays.stream(ids.replace("、", ",").split(","))
	                            .map(String::trim)
	                            .map(Integer::parseInt)
	                            .collect(Collectors.toList());

	    List<User> users = userService.selectByIds(idList);
	    Login loginUser = (Login) session.getAttribute("loginUser");

	    if (users.isEmpty()) {
	        m.addAttribute("error", "該当する社員IDがありません");
	        return "deleteForm";
	    }

	    List<Integer> userIds = users.stream().map(User::getId).collect(Collectors.toList());
	    if (loginUser != null && userIds.contains(loginUser.getId())) {
	        m.addAttribute("error", "ログイン中のIDは削除できません");
	        return "deleteForm";
	    }

	    session.setAttribute("previousUrl", "/deleteForm");
	    m.addAttribute("ids", userIds);
	    return "deleteFormConfirm";
	    }

	
	//社員情報削除(確認)画面へ遷移
	@RequestMapping("/deleteFormConfirm")
	public String deleteFormConfirm(HttpSession session, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		if (referer != null) {
			session.setAttribute("previousUrl", referer);
		}
		return "deleteFormConfirm";
	}

	//社員情報削除(確認)の戻るボタンでアクセスのあった画面に遷移
	@PostMapping("/previous")
	public String previous(HttpSession session, Model m) {
		// 保存されているURLを取得
		String previousUrl = (String) session.getAttribute("previousUrl");
		// URLが存在しない場合はデフォルトでメニュー画面に遷移
		if (previousUrl == null || previousUrl.isEmpty()) {
			return "redirect:/menu";
		}
		return "redirect:" + previousUrl;
	}

	//社員情報削除(確認)
	@PostMapping("/delete")
	public String delete(
			Model m,
			@RequestParam("ids") String idsStr) {
		List<Integer> ids = Arrays.stream(idsStr.split(","))
                .map(Integer::parseInt)
                .toList();
		service.delete(ids);
		m.addAttribute("msg", "社員情報の削除が完了しました");
		return "deleteResult";
	}

	//社員情報削除(完了)
	//メニュー画面へ遷移ボタン
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}

	//検索画面へ遷移ボタン
	@GetMapping("search")
	public String search() {
		return "search";
	}
}