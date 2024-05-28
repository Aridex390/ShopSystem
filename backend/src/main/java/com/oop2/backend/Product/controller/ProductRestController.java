package com.oop2.backend.Product.controller;

import com.oop2.backend.Product.model.Product;
import com.oop2.backend.Product.model.search.PagedResponse;
import com.oop2.backend.Product.model.search.SearchRequest;
import com.oop2.backend.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The rest controller class for Product, with the endpoints to exchange data between the frontend and the backend.
 * </p>
 * The controller is only for get purpose.
 * @author Florian Reining
 * @version 1.1
 */
@RestController
@RequestMapping("/user/product")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse> getProducts(SearchRequest request) {
        PagedResponse products = productService.getPagedProducts(request);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
