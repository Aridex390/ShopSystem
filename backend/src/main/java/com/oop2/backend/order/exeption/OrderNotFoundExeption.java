package com.oop2.backend.order.exeption;

public class OrderNotFoundExeption extends RuntimeException {
    public OrderNotFoundExeption(String msg) {
        super(msg);
    }
}
