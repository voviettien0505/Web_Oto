package com.fpoly.java.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String loginPage() {

		return "user/index";
	}

	@GetMapping("/cart")
	public String CartPage() {

		return "user/cart";
	}

	@GetMapping("/products")
	public String productsPage() {

		return "user/products";
	}

	@GetMapping("admin")
	public String adminPage() {

		return "admin/admin";
	}

	@GetMapping("/user")
	public String UserPage() {

		return "admin/user";
	}
	
	@GetMapping("/oder")
	public String OderPage() {

		return "admin/oder";
	}

}
