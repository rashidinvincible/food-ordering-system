package com.mamoon.project.food_ordering_app.repository;

import com.mamoon.project.food_ordering_app.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
