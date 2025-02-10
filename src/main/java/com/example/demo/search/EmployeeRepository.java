package com.example.demo.search;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE " +
		       "(:id IS NULL OR e.id = :id) AND " +
		       "(:name IS NULL OR e.name LIKE %:name%) AND " +
		       "(:minAge IS NULL OR e.age >= :minAge) AND " +
		       "(:maxAge IS NULL OR e.age <= :maxAge) AND " +
		       "(:startDateFrom IS NULL OR e.startDate >= :startDateFrom) AND " +
		       "(:startDateTo IS NULL OR e.startDate <= :startDateTo) AND " +
		       "(:endDateFrom IS NULL OR e.endDate >= :endDateFrom) AND " +
		       "(:endDateTo IS NULL OR e.endDate <= :endDateTo)")
		List<Employee> findEmployees(Long id, String name, Integer minAge, Integer maxAge,
		                             LocalDate startDateFrom, LocalDate startDateTo,
		                             LocalDate endDateFrom, LocalDate endDateTo);
}
