package com.oop2.backend.Product.controller;

import com.oop2.backend.Product.model.Product;
import com.oop2.backend.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The rest controller class for Product, with the endpoints to change data between the frontend and the backend.
 *
 * @author Florian Reining
 * @version 1.0
 */
@RestController("admin/product")
public class AdminProductRestController {
    private final ProductService productService;

    @Autowired
    public AdminProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = "application/json")
    public Optional<Product> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
}
