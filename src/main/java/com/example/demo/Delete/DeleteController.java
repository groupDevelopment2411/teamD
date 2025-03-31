package com.example.demo.Delete;

import java.util.ArrayList;
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
	
	//社員情報削除(入力)
	//deleteForm.htmlに遷移
	@RequestMapping("/deleteForm")
	public String deleteForm(HttpSession session, HttpServletRequest request) {
		//直前のURLをセッションに保存
		String referer = request.getHeader("Referer");
		if (referer != null) {
			session.setAttribute("previousUrl", referer);
		}
		return "deleteForm";
	}
	
	//deleteForm.htmlからdeleteFormConfirm.htmlに遷移
	//社員IDを検索してデータが存在すれば社員情報削除(確認)に遷移
	@PostMapping("/deleteSearch")
	public String deleteSearch(Model m, @RequestParam("ids") String ids, HttpSession session) {
		// 入力された社員ID文字列をカンマや全角カンマで分割し、空白を取り除き、Integer型のリストに変換
	    List<Integer> idList = Arrays.stream(ids.replace("、", ",").split(","))
	                            .map(String::trim)
	                            .map(Integer::parseInt)
	                            .collect(Collectors.toList());
	    //社員IDから社員情報を検索
	    List<User> users = userService.selectByIds(idList);
	    //セッションからログインユーザ情報を取得
	    Login loginUser = (Login) session.getAttribute("loginUser");
	    
	    //エラーメッセージを格納
	    List<String> errors = new ArrayList<>();
	    //社員IDが存在しない場合
	    if (users.isEmpty()) {
			errors.add("該当する社員IDがありません");
		}
	    //削除対象がセッションに保存されているログイン中のユーザの場合
		List<Integer> userIds = users.stream().map(User::getId).collect(Collectors.toList());
		if (loginUser != null && userIds.contains(loginUser.getId())) {
			errors.add("ログイン中のIDは削除できません");
		}
		//エラーがある場合
		if (!errors.isEmpty()) {
			m.addAttribute("errors", errors);
			return "deleteForm";
		}
		//エラーがない場合、直前のURLをセッションに保存
	    session.setAttribute("previousUrl", "/deleteForm");
	    //削除対象の社員IDを社員情報削除(確認)に渡す
	    m.addAttribute("ids", userIds);
	    return "deleteFormConfirm";
	    }

	
	//削除予定
//	@RequestMapping("/deleteFormConfirm")
//	public String deleteFormConfirm(HttpSession session, HttpServletRequest request) {
//		String referer = request.getHeader("Referer");
//		if (referer != null) {
//			session.setAttribute("previousUrl", referer);
//		}
//		return "deleteFormConfirm";
//	}

	//直前のURLに戻る
	@PostMapping("/previous")
	public String previous(HttpSession session, Model m) {
		// 保存されているURLを取得
		String previousUrl = (String) session.getAttribute("previousUrl");
		// URLが存在しない場合はメニュ-に遷移
		if (previousUrl == null || previousUrl.isEmpty()) {
			return "redirect:/menu";
		}
		return "redirect:" + previousUrl;
	}

	//社員情報削除(確認)
	//deleteFormConfirm.htmlからdeleteResult.htmlに遷移
	@PostMapping("/delete")
	public String delete(
			Model m,
			@RequestParam("ids") String idsStr) {
		// 入力された社員ID文字列をカンマで分割し、Integer型のリストに変換
		List<Integer> ids = Arrays.stream(idsStr.split(","))
                .map(Integer::parseInt)
                .toList();
		//社員情報を削除
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
	@GetMapping("idForm")
	public String idForm() {
		return "idForm";
	}
}