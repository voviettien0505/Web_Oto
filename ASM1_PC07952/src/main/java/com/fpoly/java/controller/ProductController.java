package com.fpoly.java.controller;

import com.fpoly.java.model.Product;
import com.fpoly.java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Display all products
    @GetMapping("/QLSanPham")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/QLSanPham"; // View for displaying product list
    }

    // Show form for adding a new product
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/productForm"; // Same form for both create and edit
    }

    // Handle saving a new product
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/admin/product/QLSanPham"; // Redirect to list page after saving
    }

    // Show form for editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "admin/productForm"; // Return the same form for editing
        } else {
            return "redirect:/admin/product/QLSanPham"; // Redirect if product not found
        }
    }

    // Handle updating an existing product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product productDetails) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(productDetails.getName());
            updatedProduct.setDescription(productDetails.getDescription());
            updatedProduct.setPrice(productDetails.getPrice());
            updatedProduct.setQuantity(productDetails.getQuantity());
            updatedProduct.setImage(productDetails.getImage());
            updatedProduct.setCategory(productDetails.getCategory());
            updatedProduct.setBrand(productDetails.getBrand());
            updatedProduct.setModel(productDetails.getModel());
            productService.save(updatedProduct);
        }
        return "redirect:/admin/product/QLSanPham"; // Redirect after update
    }

    // Handle deleting a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/admin/product/QLSanPham"; // Redirect after delete
    }
}
