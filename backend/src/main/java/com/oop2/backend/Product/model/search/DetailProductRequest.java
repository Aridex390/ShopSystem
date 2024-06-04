package com.oop2.backend.Product.model.search;

import com.oop2.backend.Product.model.Enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailProductRequest {
    String category;
    int size;
}
