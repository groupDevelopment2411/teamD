package com.example.demo.search;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                         @RequestParam(required = false) String startDateFrom,
                         @RequestParam(required = false) String startDateTo,
                         @RequestParam(required = false) String endDateFrom,
                         @RequestParam(required = false) String endDateTo,
                         Model model) {
    	 try {
    	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	        LocalDate parsedStartDateFrom = (startDateFrom != null && !startDateFrom.isEmpty()) ? LocalDate.parse(startDateFrom, formatter) : null;
    	        LocalDate parsedStartDateTo = (startDateTo != null && !startDateTo.isEmpty()) ? LocalDate.parse(startDateTo, formatter) : null;
    	        LocalDate parsedEndDateFrom = (endDateFrom != null && !endDateFrom.isEmpty()) ? LocalDate.parse(endDateFrom, formatter) : null;
    	        LocalDate parsedEndDateTo = (endDateTo != null && !endDateTo.isEmpty()) ? LocalDate.parse(endDateTo, formatter) : null;

    	        List<Employee> employees = service.searchEmployees(id, name, minAge, maxAge, parsedStartDateFrom, parsedStartDateTo, parsedEndDateFrom, parsedEndDateTo);
    	        model.addAttribute("employees", employees);
    	        model.addAttribute("resultCount", employees.size());
    	    } catch (DateTimeParseException e) {
    	        model.addAttribute("error", "日付の形式が正しくありません。正しい形式で入力してください。");
    	    }
    	    return "search";
    	}

    @GetMapping("/clear")
    public String clearSearch(Model model) {
        model.addAttribute("employees", null);
        model.addAttribute("resultCount", 0);
        return "search";
    }
    
    @Controller
    public class NavigationController {
        
        @GetMapping("/menu")//メニュー画面のリンク名
        public String menu() {
            return "menu"; // メニュー画面のHTML（menu.html）
        }

        @GetMapping("/register")//登録画面のリンク名
        public String register() {
            return "register"; // 登録画面のHTML名
        }
        

@		PostMapping("/deleteConfirm")//削除画面のリンク名
        public String deleteConfirm(@RequestParam List<Long> selectedIds, Model model) {
            model.addAttribute("selectedIds", selectedIds);
            return "deleteConfirm"; // 削除確認画面へ遷移
        } 
        }

    }