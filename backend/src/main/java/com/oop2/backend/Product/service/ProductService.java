package com.oop2.backend.Product.service;

import com.oop2.backend.Product.exeption.ProductNotFoundException;
import com.oop2.backend.Product.model.Enums.Category;
import com.oop2.backend.Product.model.Product;
import com.oop2.backend.Product.model.ProductRequest;
import com.oop2.backend.Product.model.ProductResponse;
import com.oop2.backend.Product.model.search.PagedResponse;
import com.oop2.backend.Product.model.search.SearchRequest;
import com.oop2.backend.Product.model.search.util.SearchRequestUtil;
import com.oop2.backend.Product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    private static final int PAGE_SIZE = 12;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * The methode give you all products for the shop that currently existing.
     *
     * @return a List of all Products as @{@link Product}
     */
    public PagedResponse getPagedProducts(SearchRequest request) {
        Page<Product> response = productRepo.findAll(SearchRequestUtil.toPageRequest(request));
        PagedResponse pagedResponse;
        if(response.isEmpty()) {
            pagedResponse = new PagedResponse(Collections.emptyList(), 0, response.getTotalElements());
        } else {
            List<Product> products = response.getContent();
            pagedResponse = new PagedResponse(products, response.getSize(), response.getTotalElements());
        }


        return pagedResponse;
    }

    /**
     * The methode search a product by his id.
     *
     * @param id the id of a product of @{@link Long}
     * @return a @{@link Product}
     */
    public ProductResponse getProductById(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " was not found"));
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .weight(product.getWeight())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .category(product.getCategory())
                .imageUrl(product.getImage())
                .build();

        return response;
    }

    /**
     *
     * @param request take all available information's of a @{@link ProductRequest}
     * @return the added product
     */
    public ProductResponse addProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .weight(request.getWeight())
                .price(request.getPrice())
                .currency(request.getCurrency())
                .category(request.getCategory())
                .build();

        product = productRepo.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .weight(product.getWeight())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .category(product.getCategory())
                .build();
    }

    /**
     * The methode updates a product.
     *
     * @param request takes the edit @{@link ProductRequest}
     */
    public ProductResponse updateProduct(ProductResponse request) {
        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .weight(request.getWeight())
                .price(request.getPrice())
                .currency(request.getCurrency())
                .category(request.getCategory())
                .image(request.getImageUrl())
                .build();

        product = productRepo.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .weight(product.getWeight())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .category(product.getCategory())
                .imageUrl(product.getImage())
                .build();
    }

    /**
     * The methode deletes the product.
     *
     * @param id the id of a product of @{@link Long}
     */
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    // TODO: Pagination

    /** The methode get all products for a category or throws a @{@link ProductNotFoundException}.
     *
     * @param category @{@link Category}
     * @return a @{@link List} of products
     */
    public List<Product> getProductsByCategory(Category category) {
        return new ArrayList<>(productRepo.findProductsByCategory(category).orElseThrow(() -> new ProductNotFoundException("Products with " + category + " was not found or " + category + " don't exist")));
    }
}
