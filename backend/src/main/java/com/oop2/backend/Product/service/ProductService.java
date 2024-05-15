package com.oop2.backend.Product.service;

import com.oop2.backend.Product.model.Product;
import com.oop2.backend.Product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: Documentation
@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productRepo.findAll());
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public void updateProduct(Product product) {
        productRepo.UpdateProduct(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
