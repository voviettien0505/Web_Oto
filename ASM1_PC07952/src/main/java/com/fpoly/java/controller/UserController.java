package com.fpoly.java.controller;

import com.fpoly.java.model.Employee;
import com.fpoly.java.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Hiển thị danh sách tất cả người dùng
    @GetMapping
    public String getAllUsers(Model model) {
        List<Employee> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user";
    }

    // Xử lý tìm kiếm theo tên
    @GetMapping("/search")
    public String searchUsers(@RequestParam("name") String name, Model model) {
        List<Employee> users = userService.searchByName(name);
        model.addAttribute("users", users);
        return "admin/user";  // Trả về cùng view với danh sách người dùng
    }

    // Hiển thị form chỉnh sửa người dùng
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Employee employee = userService.findById(id);
        model.addAttribute("employee", employee);
        return "admin/edit_user";  // View cho form chỉnh sửa
    }

    // Xử lý cập nhật người dùng
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("employee") Employee employee) {
        userService.updateUser(employee);
        return "redirect:/admin/users";
    }

    // Xử lý xóa người dùng
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
