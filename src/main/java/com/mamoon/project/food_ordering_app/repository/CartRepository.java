package com.mamoon.project.food_ordering_app.repository;

import com.mamoon.project.food_ordering_app.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
