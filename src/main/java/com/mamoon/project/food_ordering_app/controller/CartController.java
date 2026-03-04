package com.mamoon.project.food_ordering_app.controller;


import com.mamoon.project.food_ordering_app.dto.CartItemRequestDTO;
import com.mamoon.project.food_ordering_app.dto.CartResponseDTO;
import com.mamoon.project.food_ordering_app.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponseDTO> getCartByCustomerId(@PathVariable Long customerId)
    {
        return ResponseEntity.ok(cartService.getCartByCustomerId(customerId));
    }

    @PostMapping("/{customerId}/items")
    public ResponseEntity<CartResponseDTO> addItemToCart( @PathVariable Long customerId, @RequestBody CartItemRequestDTO request)
    {
        return new ResponseEntity<>(cartService.addItemToCart(customerId, request), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}/items")
    public ResponseEntity<CartResponseDTO> updateItemQuantity(@PathVariable Long customerId, @RequestBody CartItemRequestDTO request)
    {
        return ResponseEntity.ok(cartService.updateItemQuantity(customerId, request));
    }

    @DeleteMapping("/{customerId}/items/{foodItemId}")
    public ResponseEntity<CartResponseDTO> removeItemFromCart(@PathVariable Long customerId, @PathVariable Long foodItemId) {

        return ResponseEntity.ok(
                cartService.removeItemFromCart(customerId, foodItemId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long customerId) {

        cartService.clearCart(customerId);
        return ResponseEntity.noContent().build();
    }


}
