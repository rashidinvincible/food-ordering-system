package com.mamoon.project.food_ordering_app.repository;


import com.mamoon.project.food_ordering_app.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
