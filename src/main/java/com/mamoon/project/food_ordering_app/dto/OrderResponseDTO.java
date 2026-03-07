package com.mamoon.project.food_ordering_app.dto;

import com.mamoon.project.food_ordering_app.entity.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {

    Long orderId;
    Long customerId;
    OrderStatus orderStatus;
    BigDecimal orderAmount;
    LocalDateTime orderDate;
    List<OrderItemResponseDTO> items;

}
