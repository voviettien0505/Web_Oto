package com.fpoly.java.repository;

import com.fpoly.java.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Tìm kiếm theo tên (không phân biệt hoa thường)
    List<Employee> findByNameContainingIgnoreCase(String name);
}
