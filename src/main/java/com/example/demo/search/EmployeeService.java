package com.example.demo.search;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> searchEmployees(String id, String name, String minAge, String maxAge, String startDate, String endDate) {
        // 入力チェック
        validateInputs(id, minAge, maxAge, startDate, endDate);

        // 検索条件を処理
        Long employeeId = (id != null && !id.isEmpty()) ? Long.parseLong(id) : null;
        Integer min = (minAge != null && !minAge.isEmpty()) ? Integer.parseInt(minAge) : null;
        Integer max = (maxAge != null && !maxAge.isEmpty()) ? Integer.parseInt(maxAge) : null;
        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")) : null;
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")) : null;

        // リポジトリに条件を渡して検索
        return repository.findEmployees(employeeId, name, min, max, start, end);
    }

    private void validateInputs(String id, String minAge, String maxAge, String startDate, String endDate) {
        // ID, 年齢, 日付の形式チェック
        if (id != null && !id.matches("\\d+")) {
            throw new IllegalArgumentException("社員IDは数値のみを許可します。");
        }
        if ((minAge != null && !minAge.matches("\\d+")) || (maxAge != null && !maxAge.matches("\\d+"))) {
            throw new IllegalArgumentException("年齢は数値のみを許可します。");
        }
        if ((startDate != null && !startDate.matches("\\d{4}/\\d{2}/\\d{2}")) ||
            (endDate != null && !endDate.matches("\\d{4}/\\d{2}/\\d{2}"))) {
            throw new IllegalArgumentException("日付はyyyy/MM/dd形式で入力してください。");
        }
    }
}
