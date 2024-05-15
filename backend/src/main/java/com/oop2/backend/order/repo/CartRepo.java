package com.oop2.backend.order.repo;

import com.oop2.backend.order.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Documentation
public interface CartRepo extends JpaRepository<Cart, Long> {
}
