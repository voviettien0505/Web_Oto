package com.fpoly.java.service;

import com.fpoly.java.model.Employee;
import com.fpoly.java.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final EmployeeRepository employeeRepository;

    public UserService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Lấy danh sách tất cả người dùng
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Tìm kiếm người dùng theo tên (không phân biệt hoa thường)
    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Tìm người dùng theo ID
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Cập nhật người dùng
    public void updateUser(Employee employee) {
        employeeRepository.save(employee);
    }

    // Xóa người dùng theo ID
    public void deleteUser(Long id) {
        employeeRepository.deleteById(id);
    }
}
