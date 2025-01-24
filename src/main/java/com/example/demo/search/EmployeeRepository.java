package com.example.demo.search;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
    public List<Employee> findEmployees(Long id, String name, Integer minAge, Integer maxAge, LocalDate startDate, LocalDate endDate) {
        return List.of(); // ダミー実装
    }
}
