package com.fpoly.java.controller;

import com.fpoly.java.model.Customer;
import com.fpoly.java.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Hiển thị danh sách khách hàng và form
    @GetMapping("/admin")
    public String showCustomerList(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer()); // Để thêm khách hàng mới
        return "admin/manageCustomer";  // Render view "admin/manageCustomer.html"
    }

    // Lưu hoặc cập nhật khách hàng
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.createCustomer(customer); // Lưu hoặc cập nhật khách hàng
        return "redirect:/customer/admin";
    }

    // Chỉnh sửa khách hàng (tải dữ liệu vào form)
    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id).orElse(new Customer());
        model.addAttribute("customer", customer); // Gửi dữ liệu khách hàng đến form
        return "admin/manageCustomer"; // Quay lại form
    }

    // Xóa khách hàng
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/admin";
    }
}
