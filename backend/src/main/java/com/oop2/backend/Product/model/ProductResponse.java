package com.oop2.backend.Product.model;


import com.oop2.backend.Product.model.Enums.Category;
import com.oop2.backend.Product.model.Enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private double weight;
    private double price;
    private Currency currency;
    private Category category;
    private String imageUrl;

}
