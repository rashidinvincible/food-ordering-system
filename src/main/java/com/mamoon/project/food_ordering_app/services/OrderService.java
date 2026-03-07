package com.mamoon.project.food_ordering_app.services;

import com.mamoon.project.food_ordering_app.dto.OrderResponseDTO;
import com.mamoon.project.food_ordering_app.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponseDTO placeOrder(Long customerId);
    OrderResponseDTO getOrderById(Long orderId);
    List<OrderResponseDTO> getOrdersByCustomerId(Long customerId);
    OrderResponseDTO cancelOrder(Long orderId);
    OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status);



}
