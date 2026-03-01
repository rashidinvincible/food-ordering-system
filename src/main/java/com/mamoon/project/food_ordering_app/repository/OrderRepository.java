package com.mamoon.project.food_ordering_app.repository;

import com.mamoon.project.food_ordering_app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
