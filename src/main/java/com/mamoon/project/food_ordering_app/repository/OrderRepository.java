package com.mamoon.project.food_ordering_app.repository;

import com.mamoon.project.food_ordering_app.entity.Order;
import com.mamoon.project.food_ordering_app.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long id);
    List<Order> findByOrderStatus(OrderStatus status);
    List<Order> findByCustomerIdAndOrderStatus(Long customerId, OrderStatus status);

}
