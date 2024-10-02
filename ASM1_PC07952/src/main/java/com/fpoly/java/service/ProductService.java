package com.fpoly.java.service;

import com.fpoly.java.model.Product;
import com.fpoly.java.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Fetch all products
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Find product by ID
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    // Save a new or updated product
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product by ID
    public Product update(Long id, Product productDetails) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();

            // Update product fields with new values
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setQuantity(productDetails.getQuantity());
            existingProduct.setImage(productDetails.getImage());
            existingProduct.setCategory(productDetails.getCategory());
            existingProduct.setBrand(productDetails.getBrand());
            existingProduct.setModel(productDetails.getModel());

            // Save the updated product
            return productRepository.save(existingProduct);
        } else {
            // Handle product not found case (optional)
            return null;  // Or throw an exception
        }
    }

    // Delete product by ID
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
