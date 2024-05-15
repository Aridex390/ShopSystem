package com.oop2.backend.Product.repo;

import com.oop2.backend.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Documentation
public interface ProductRepo extends JpaRepository<Product, Long> {
    void UpdateProduct(Product product);
}
