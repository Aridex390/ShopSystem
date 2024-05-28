package com.oop2.backend.Product.model.search;

import com.oop2.backend.Product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse {
    private List<Product> products;
    private long count;
    private long totalCount;
}
