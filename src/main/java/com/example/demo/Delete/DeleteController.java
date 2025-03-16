package com.example.demo.Delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//コントローラークラス
@Controller
public class DeleteController {
	//インスタンス
	@Autowired
	private DeleteService service;
	
	@RequestMapping("/deleteForm")
	public String deleteForm() {
		return "deleteForm";
	}
	
	@PostMapping("/delete")
	public String delete(
			Model m,
			@RequestParam("id") String id
			) {
		int numId = Integer.parseInt(id);
		service.delete(numId);
		m.addAttribute("msg", "社員情報の削除が完了しました");
		return "deleteResult";
	}
}
