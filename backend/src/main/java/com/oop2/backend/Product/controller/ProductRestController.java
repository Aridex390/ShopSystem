package com.oop2.backend.Product.controller;

import com.oop2.backend.Product.model.Product;
import com.oop2.backend.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The rest controller class for Product, with the endpoints to change data between the frontend and the backend.
 * </p>
 * The controller is only for get purpose.
 * @author Florian Reining
 * @version 1.0
 */
@RestController("product")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "products", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/product-{id}", method = RequestMethod.GET, produces = "application/json")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
