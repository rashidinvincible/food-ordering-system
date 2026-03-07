package com.mamoon.project.food_ordering_app.services;

import com.mamoon.project.food_ordering_app.dto.OrderResponseDTO;
import com.mamoon.project.food_ordering_app.entity.*;
import com.mamoon.project.food_ordering_app.entity.enums.OrderStatus;
import com.mamoon.project.food_ordering_app.exception.ResourceNotFoundException;
import com.mamoon.project.food_ordering_app.repository.CartRepository;
import com.mamoon.project.food_ordering_app.repository.CustomerRepository;
import com.mamoon.project.food_ordering_app.repository.OrderItemRepository;
import com.mamoon.project.food_ordering_app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;


    @Override
    public OrderResponseDTO placeOrder(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer with id " + customerId + " not found"));
        Cart cart = customer.getCart();
        if(cart == null)
        {
            throw new ResourceNotFoundException("Cart with id " + customerId + " not found");
        }

        List<CartItem> cartItems = cart.getItems();

        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty. Cannot place order.");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PLACED);

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems)
        {
            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setFoodItem(cartItem.getFoodItem());
            orderItem.setQuantity(cartItem.getQuantity());
            BigDecimal price = cartItem.getPriceAtAdd();
            orderItem.setPriceAtOrder(price);
            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(orderItem.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);
            order.getOrderItems().add(orderItem);

        }
        order.setOrderAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);
        cart.getItems().clear();
        cartRepository.save(cart);
        return modelMapper.map(savedOrder, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    @Override
    public List<OrderResponseDTO> getOrdersByCustomerId(Long customerId) {
       customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer with id " + customerId + " not found"));
       List<Order> orders = orderRepository.findByCustomerId(customerId);
       List<OrderResponseDTO> response= orders.stream().map(order -> modelMapper.map(order, OrderResponseDTO.class)).toList();
       return response;

    }

    @Override
    public OrderResponseDTO cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));
        order.setOrderStatus(OrderStatus.CANCELLED);
        order =  orderRepository.save(order);
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));
        if(order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Order with id " + orderId + " is already cancelled");
        }
        if(order.getOrderStatus() == OrderStatus.DELIVERED)
        {
            throw new IllegalStateException("Order with id " + orderId + " is already delivered");
        }
        order.setOrderStatus(status);
        order = orderRepository.save(order);
        return modelMapper.map(order, OrderResponseDTO.class);
    }


}

