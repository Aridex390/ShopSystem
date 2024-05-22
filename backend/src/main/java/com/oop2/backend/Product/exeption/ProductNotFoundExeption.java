package com.oop2.backend.Product.exeption;

public class ProductNotFoundExeption extends RuntimeException {
    public ProductNotFoundExeption(String s) {
        super(s);
    }
}
