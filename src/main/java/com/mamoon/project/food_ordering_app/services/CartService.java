package com.mamoon.project.food_ordering_app.services;

import com.mamoon.project.food_ordering_app.dto.CartItemRequestDTO;
import com.mamoon.project.food_ordering_app.dto.CartItemResponseDTO;
import com.mamoon.project.food_ordering_app.dto.CartResponseDTO;

public interface CartService {

    CartResponseDTO getCartByCustomerId(Long customerId);
    CartResponseDTO addItemToCart(Long customerId, CartItemRequestDTO cartItemRequestDTO);
    CartResponseDTO updateItemQuantity(Long customerId, CartItemRequestDTO cartItemRequestDTO);
    CartResponseDTO removeItemFromCart(Long customerId, Long foodItemId);
    void clearCart(Long customerId);

}
