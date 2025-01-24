package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String id,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) String minAge,
                         @RequestParam(required = false) String maxAge,
                         @RequestParam(required = false) String startDate,
                         @RequestParam(required = false) String endDate,
                         Model model) {
        try {
            // 検索実行
            List<Employee> employees = service.searchEmployees(id, name, minAge, maxAge, startDate, endDate);

            // 結果をモデルに追加
            model.addAttribute("employees", employees);
            model.addAttribute("resultCount", employees.size());
        } catch (IllegalArgumentException e) {
            // 入力エラー時
            model.addAttribute("error", e.getMessage());
        }
        return "search";
    }

    @GetMapping("/clear")
    public String clearSearch(Model model) {
        // フォームのリセット（必要であれば初期値を設定）
        model.addAttribute("employees", null);
        model.addAttribute("resultCount", 0);
        return "search";
    }
}
