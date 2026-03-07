package com.mamoon.project.food_ordering_app.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDTO {

    Long foodItemId;
    String foodItemName;
    Integer quantity;
    BigDecimal priceAtOrder;
    public BigDecimal getTotalPrice() {
        if (priceAtOrder == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return priceAtOrder.multiply(BigDecimal.valueOf(quantity));
    }
}
