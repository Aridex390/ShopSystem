package com.oop2.backend.Product.repo;

import com.oop2.backend.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface hold the methods for special operation in a service and is extended by @{@link JpaRepository} for basic database operations.
 *
 * @author Florian Reining
 * @version 1.1
 */
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> UpdateProduct(Product product);
}
