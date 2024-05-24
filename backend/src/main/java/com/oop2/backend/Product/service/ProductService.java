package com.oop2.backend.Product.service;

import com.oop2.backend.Product.exeption.ProductNotFoundException;
import com.oop2.backend.Product.model.Enums.Category;
import com.oop2.backend.Product.model.Product;
import com.oop2.backend.Product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This service class handles all the action for @{@link Product}
 *
 * @author Florian Reining
 * @version 1.1
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
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " was not found"));
    }

    /**
     *
     * @param product take all available information's of a @{@link Product}
     * @return the added product
     */
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    /**
     * The methode updates a product.
     *
     * @param product takes the edit @{@link Product}
     */
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    /**
     * The methode deletes the product.
     *
     * @param id the id of a product of @{@link Long}
     */
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    /** The methode get all products for a category or throws a @{@link ProductNotFoundException}.
     *
     * @param category @{@link Category}
     * @return a @{@link List} of products
     */
    public List<Product> getProductsByCategory(Category category) {
        return new ArrayList<>(productRepo.findProductsByCategory(category).orElseThrow(() -> new ProductNotFoundException("Products with " + category + " was not found or " + category + " don't exist")));
    }
}
