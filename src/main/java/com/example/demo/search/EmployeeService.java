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
        validateInputs(id, minAge, maxAge, startDate, endDate);
        Long employeeId = (id != null && !id.isEmpty()) ? Long.parseLong(id) : null;
        Integer min = (minAge != null && !minAge.isEmpty()) ? Integer.parseInt(minAge) : null;
        Integer max = (maxAge != null && !maxAge.isEmpty()) ? Integer.parseInt(maxAge) : null;
        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")) : null;
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")) : null;

        return repository.findEmployees(employeeId, name, min, max, start, end);
    }

    private void validateInputs(String id, String minAge, String maxAge, String startDate, String endDate) {
        if (id != null && !id.matches("\\d+")) {
            throw new IllegalArgumentException("");
        }
        if ((minAge != null && !minAge.matches("\\d+")) || (maxAge != null && !maxAge.matches("\\d+"))) {
            throw new IllegalArgumentException("");
        }
        if ((startDate != null && !startDate.matches("\\d{4}/\\d{2}/\\d{2}")) ||
            (endDate != null && !endDate.matches("\\d{4}/\\d{2}/\\d{2}"))) {
            throw new IllegalArgumentException("");
        }
    }
}
