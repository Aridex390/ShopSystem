package com.oop2.backend.Product.service;

import com.oop2.backend.Product.model.Product;
import com.oop2.backend.Product.repo.ProductRepo;
import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This service class handles all the action for @{@link Product}
 *
 * @author Florian Reining
 * @version 1.0
 */
@Service
public class ProductService {
    /** Dependency to @{@link ProductRepo} */
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * The methode give you all products for the shop that currently existing.
     *
     * @return a List of all Products as @{@link Product}
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(productRepo.findAll());
    }

    /**
     * The methode search a product by his id.
     *
     * @param id the id of a product of @{@link Long}
     * @return a @{@link Product}
     */
    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    /**
     *
     * @param product take all available information's of a @{@link Product}
     * @return
     */
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    /**
     * The methode updates a product.
     *
     * @param product takes the edit @{@link Product}
     */
    public void updateProduct(Product product) {
        productRepo.UpdateProduct(product);
    }

    /**
     * The methode deletes the product.
     *
     * @param id the id of a product of @{@link Long}
     */
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
