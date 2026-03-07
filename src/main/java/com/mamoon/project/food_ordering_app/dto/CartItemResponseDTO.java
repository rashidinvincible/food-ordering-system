package com.mamoon.project.food_ordering_app.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemResponseDTO {

    Long foodItemId;
    String foodItemName;
    BigDecimal foodItemPrice;
    Long quantity;
    BigDecimal subtotal;
}
