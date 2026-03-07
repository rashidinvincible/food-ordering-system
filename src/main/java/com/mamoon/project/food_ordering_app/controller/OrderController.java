package com.mamoon.project.food_ordering_app.controller;

import com.mamoon.project.food_ordering_app.dto.OrderResponseDTO;
import com.mamoon.project.food_ordering_app.entity.enums.OrderStatus;
import com.mamoon.project.food_ordering_app.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place/{customerId}")
    public ResponseEntity<OrderResponseDTO> placeOrder(@PathVariable Long customerId)
    {
        return ResponseEntity.ok(orderService.placeOrder(customerId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId)
    {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByCustomer(@PathVariable Long customerId)
    {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long orderId)
    {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus orderStatus)
    {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, orderStatus));
    }



}
