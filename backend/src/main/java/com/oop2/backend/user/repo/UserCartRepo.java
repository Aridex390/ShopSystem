package com.oop2.backend.user.repo;

import com.oop2.backend.user.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Documentation
public interface UserCartRepo extends JpaRepository<UserCart, Long> {
}
