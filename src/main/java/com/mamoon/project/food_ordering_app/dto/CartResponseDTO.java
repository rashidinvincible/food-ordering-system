package com.mamoon.project.food_ordering_app.dto;

import com.mamoon.project.food_ordering_app.entity.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartResponseDTO {

    Long cartId;
    Long customerId;
    List<CartItemResponseDTO> cartItems;
    BigDecimal totalAmount;
}
