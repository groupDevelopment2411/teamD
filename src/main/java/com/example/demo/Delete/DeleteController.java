package com.example.demo.Delete;

import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//コントローラークラス
@Controller
public class DeleteController {
	//インスタンス化
	@Autowired
	private DeleteService service;

	//社員情報削除画面へ遷移
	@RequestMapping("/deleteForm")
	public String deleteForm(HttpSession session, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		if (referer != null) {
			session.setAttribute("previousUrl", referer);
		}
		return "deleteForm";
	}

	//社員情報登録(入力)の戻るボタンでアクセスのあった画面に遷移
	@PostMapping("/previous")
	public String previous(HttpSession session) {
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