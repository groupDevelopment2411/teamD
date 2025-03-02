package com.example.demo.search;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    
    @GetMapping("/search")
    public String showSearchPage() {
        return "search"; 
    }

    
    @PostMapping("/search")
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            // 入力チェック
            if (id != null && !id.matches("\\d*")) {
                model.addAttribute("error", "社員IDは数字ではありません");
                return "search";
            }
            if ((minAge != null && !minAge.matches("\\d*")) || (maxAge != null && !maxAge.matches("\\d*"))) {
                model.addAttribute("error", "年齢は数字ではありません");
                return "search";
            }
            
            LocalDate parsedStartDateFrom = parseDate(startDateFrom, formatter, model);
            LocalDate parsedStartDateTo = parseDate(startDateTo, formatter, model);
            LocalDate parsedEndDateFrom = parseDate(endDateFrom, formatter, model);
            LocalDate parsedEndDateTo = parseDate(endDateTo, formatter, model);

            if (model.containsAttribute("error")) {
                return "search";
            }

            List<Employee> employees = service.searchEmployees(id, name, minAge, maxAge, parsedStartDateFrom, parsedStartDateTo, parsedEndDateFrom, parsedEndDateTo);
            model.addAttribute("employees", employees);
            model.addAttribute("resultCount", employees.size());
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "日付入力が誤っています");
            return "search";
        }
        return "search";
    }

    private LocalDate parseDate(String date, DateTimeFormatter formatter, Model model) {
        if (date != null && !date.isEmpty()) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                model.addAttribute("error", "日付入力が誤っています");
            }
        }
        return null;
    }

    @GetMapping("/clear")
    public String clearSearch(Model model) {
        model.addAttribute("employees", null);
        model.addAttribute("resultCount", 0);
        return "search";
    }

    @Controller
    public class NavigationController {

        @GetMapping("/menu")
        public String menu() {
            return "menu";
        }

        @GetMapping("/register")
        public String register() {
            return "register";
        }

        @PostMapping("/delete")
        public String delete(@RequestParam(required = false) List<Long> selectedIds, Model model) {
            model.addAttribute("selectedIds", selectedIds);
            return "delete";
        }
        @GetMapping("/update/{id}")
        public String updateEmployee(@PathVariable Long id, Model model) {
            Employee employee = service.getEmployeeById(id);
            model.addAttribute("employee", employee);
            return "update"; 
        }
    }
}

