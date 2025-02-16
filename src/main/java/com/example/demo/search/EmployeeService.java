package com.example.demo.search;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> searchEmployees(String id, String name, String minAge, String maxAge, 
                                          LocalDate startDateFrom, LocalDate startDateTo, 
                                          LocalDate endDateFrom, LocalDate endDateTo) {
        Long employeeId = (id != null && !id.isEmpty()) ? Long.parseLong(id) : null;
        Integer min = (minAge != null && !minAge.isEmpty()) ? Integer.parseInt(minAge) : null;
        Integer max = (maxAge != null && !maxAge.isEmpty()) ? Integer.parseInt(maxAge) : null;

        return repository.findEmployees(employeeId, name, min, max, startDateFrom, startDateTo, endDateFrom, endDateTo);
    }
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = repository.findById(id);
        return employee.orElse(null);
    }
}
