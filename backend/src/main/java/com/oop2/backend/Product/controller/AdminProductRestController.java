package com.oop2.backend.Product.controller;

import com.oop2.backend.Product.model.Product;
import com.oop2.backend.Product.model.ProductRequest;
import com.oop2.backend.Product.model.ProductResponse;
import com.oop2.backend.Product.model.search.PagedResponse;
import com.oop2.backend.Product.model.search.SearchRequest;
import com.oop2.backend.Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The rest controller class for Product, with the endpoints to exchange data between the frontend and the backend.
 *
 * @author Florian Reining
 * @version 1.1
 */
@RestController
@RequestMapping("/admin/product")
public class AdminProductRestController {
    private final ProductService productService;

    // TODO: Separate the models and dont user the JPA entities as response and request model

    @Autowired
    public AdminProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse> getProducts(SearchRequest request) {
        PagedResponse products = productService.getPagedProducts(request);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        ProductResponse newProduct = productService.addProduct(request);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PostMapping(value = "/add-more")
    public ResponseEntity<List<ProductResponse>> addProductMore(@RequestBody ProductRequest[] request) {
        List<ProductResponse> newProducts = new ArrayList<>();
        for (ProductRequest productRequest : request) {
            ProductResponse newProduct = productService.addProduct(productRequest);
            newProducts.add(newProduct);
        }

        return new ResponseEntity<>(newProducts, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductResponse request) {
        ProductResponse updatedProduct = productService.updateProduct(request);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
