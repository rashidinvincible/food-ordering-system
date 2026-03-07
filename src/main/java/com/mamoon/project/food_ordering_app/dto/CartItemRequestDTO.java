package com.mamoon.project.food_ordering_app.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemRequestDTO {
    @NotNull(message = "Food Item ID is required")
    private Long foodItemId;

    @Min(value=1,message="Quantity must be at least 1")
    private Long quantity;

}
