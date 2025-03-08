package com.example.demo.Entry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//コントローラークラス
@Controller
public class EntryController {
	//インスタンス作成
	@Autowired
	private EntryService service;
	
	//社員情報入力フォーム
	@RequestMapping("/entryForm")
	public String entryForm() {
		return "entryForm";
	}
	//社員情報登録(入力)
	@PostMapping("/confirm")
	public String userConfirm(
			Model m,
			RedirectAttributes r,
			@RequestParam("name") String name,
			@RequestParam("age") String age,
			@RequestParam("password") String password,
			@RequestParam("passwordConfirm") String passwordConfirm
			) {
		//エラーメッセージを格納
		List<String> errors = new ArrayList<>();
		//社員名入力チェック
		if(name == null || name.trim().isEmpty()) {
			errors.add("社員名を入力してください");
		}
		//年齢入力チェック(数値のみ)
		int numAge = 0;
		try {
			numAge = Integer.parseInt(age);
		}catch(NumberFormatException e) {
			errors.add("年齢は数値で入力してください");
		}
		//パスワード入力チェック(半角英数のみ、大文字含む、混合８文字以上)
		if(password == null || password.trim().isEmpty()) {
			errors.add("パスワードの入力は必須です");
		}else if(password.length() < 8) {
			errors.add("パスワードは８文字以上で入力してください");
		}else if(!password.matches("^[a-zA-Z0-9]+$")) {
			errors.add("パスワードは半角英数字で入力してください");
		}else if(password.matches("^[0-9]{8,}$")) {
			errors.add("パスワードは数字のみでなく、英字も含めてください");
		}else if(password.matches("^[a-zA-Z]{8,}$")) {
			errors.add("パスワードは英字のみでなく、数字も含めてください");
		}
		//パスワード相関チェック
		if(!password.equals(passwordConfirm)) {
			errors.add("パスワードが一致しません");
		}
		//エラーメッセージがある場合、出力する
		if(!errors.isEmpty()) {
			r.addFlashAttribute("errors", errors);
			return "redirect:/entryForm";
		}
		//パスワードマスキング用変数
		String passwordMasked = "●".repeat(password.length());
		
		m.addAttribute("name", name);
		m.addAttribute("age", numAge);
		m.addAttribute("passwordMasked", passwordMasked);
		m.addAttribute("password", password);
		m.addAttribute("passwordConfirm", passwordConfirm);
		return "entryFormConfirm";
	}
	
	//社員情報登録(入力)の戻るボタンでアクセスのあった画面に遷移
	
	
	//社員情報登録(確認)
	@PostMapping("/entry")
	public String userEntry(
			Model m,
			@RequestParam("name") String name,
			@RequestParam("age") int age,
			@RequestParam("password") String password
			) {
		Entry entry = new Entry(name, age, password);
		service.insert(entry);
		
		m.addAttribute("msg", "社員情報の登録が完了しました");
		return "entryResult";
	}
	//社員情報登録(確認)戻るボタンでentryFormに遷移
	@PostMapping("/back")
	public String backToEntryForm(
			RedirectAttributes r,
			@RequestParam("name") String name,
			@RequestParam("age") String age,
			@RequestParam("password") String password,
			@RequestParam("passwordConfirm") String passwordConfirm
			) {
		r.addFlashAttribute("name", name);
		r.addFlashAttribute("age", age);
		r.addFlashAttribute("password", password);
		r.addFlashAttribute("passwordConfirm", passwordConfirm);
		
		return "redirect:/entryForm";
	}
	
	//社員情報登録(完了)
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
