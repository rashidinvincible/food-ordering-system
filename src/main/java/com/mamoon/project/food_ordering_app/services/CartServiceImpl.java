package com.mamoon.project.food_ordering_app.services;

import com.mamoon.project.food_ordering_app.dto.CartItemRequestDTO;
import com.mamoon.project.food_ordering_app.dto.CartItemResponseDTO;
import com.mamoon.project.food_ordering_app.dto.CartResponseDTO;
import com.mamoon.project.food_ordering_app.entity.Cart;
import com.mamoon.project.food_ordering_app.entity.CartItem;
import com.mamoon.project.food_ordering_app.entity.Customer;
import com.mamoon.project.food_ordering_app.entity.FoodItem;
import com.mamoon.project.food_ordering_app.exception.ResourceNotFoundException;
import com.mamoon.project.food_ordering_app.repository.CartItemRepository;
import com.mamoon.project.food_ordering_app.repository.CartRepository;
import com.mamoon.project.food_ordering_app.repository.CustomerRepository;
import com.mamoon.project.food_ordering_app.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final FoodItemRepository foodItemRepository;

    private CartResponseDTO mapToCartResponseDTO(Cart cart)
    {
        List<CartItemResponseDTO> itemResponses = cart.getItems()
                .stream()
                .map(item -> {
                    CartItemResponseDTO dto = new CartItemResponseDTO();
                    dto.setFoodItemId(item.getFoodItem().getId());
                    dto.setFoodItemName(item.getFoodItem().getName());
                    dto.setFoodItemPrice(item.getFoodItem().getPrice());
                    dto.setQuantity(item.getQuantity());

                    BigDecimal subTotal = item.getFoodItem()
                            .getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()));
                    dto.setSubtotal(subTotal);

                    return dto;
                })
                .toList();

        BigDecimal totalAmount = itemResponses.stream()
                .map(CartItemResponseDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(cart.getId());
        response.setCustomerId(cart.getCustomer().getId());
        response.setCartItems(itemResponses);
        response.setTotalAmount(totalAmount);

        return response;
    }

    @Override
    public CartResponseDTO getCartByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer not found with id " + customerId));
        Cart cart = customer.getCart();
        if (cart == null) {
            CartResponseDTO emptyResponse = new CartResponseDTO();
            emptyResponse.setCartId(null);
            emptyResponse.setCustomerId(customerId);
            emptyResponse.setCartItems(new ArrayList<>());
            emptyResponse.setTotalAmount(BigDecimal.valueOf(0.0));

            return emptyResponse;
        }

        return mapToCartResponseDTO(cart);
    }

    @Override
    public CartResponseDTO addItemToCart(Long customerId, CartItemRequestDTO cartItemRequestDTO) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer Not Found with id: " + customerId));
        Cart cart = customer.getCart();
        if(cart==null)
        {
            cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
        }

        FoodItem foodItem = foodItemRepository.findById(cartItemRequestDTO.getFoodItemId()).orElseThrow(()->new ResourceNotFoundException("Food Item Not Found with id: " + cartItemRequestDTO.getFoodItemId()));

        if (cartItemRequestDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        CartItem existingItem = cart.getItems().stream().filter(item -> item.getFoodItem().getId().equals(cartItemRequestDTO.getFoodItemId())).findFirst().orElse(null);

        if(existingItem!=null)
        {
            Long newQuantity = existingItem.getQuantity() + cartItemRequestDTO.getQuantity();

            existingItem.setQuantity(newQuantity);


        }
        else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setFoodItem(foodItem);
            newItem.setQuantity(cartItemRequestDTO.getQuantity());
            newItem.setPriceAtAdd(foodItem.getPrice());
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);

        return mapToCartResponseDTO(cart);
    }

    @Override
    public CartResponseDTO updateItemQuantity(Long customerId, CartItemRequestDTO cartItemRequestDTO) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer Not Found with id: " + customerId));
        Cart cart = customer.getCart();
        if(cart==null)
        {
            throw new ResourceNotFoundException("Cart not found with id " + customerId);
        }

        CartItem existingItem = cart.getItems().stream().filter(item -> item.getFoodItem().getId().equals(cartItemRequestDTO.getFoodItemId())).findFirst().orElseThrow(()->new ResourceNotFoundException("Food Item Not Found with id: " + cartItemRequestDTO.getFoodItemId()));
        if (cartItemRequestDTO.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        if (cartItemRequestDTO.getQuantity() == 0) {
            cart.getItems().remove(existingItem);
        } else {
            existingItem.setQuantity(cartItemRequestDTO.getQuantity());
        }

        cartRepository.save(cart);
        return mapToCartResponseDTO(cart);
    }

    @Override
    public CartResponseDTO removeItemFromCart(Long customerId, Long foodItemId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer Not Found with id: " + customerId));
        Cart cart = customer.getCart();
        if(cart==null)
        {
            throw new ResourceNotFoundException("Cart not found with id " + customerId);
        }

        CartItem existingItem = cart.getItems().stream().filter(item -> item.getFoodItem().getId().equals(foodItemId)).findFirst().orElseThrow(()->new ResourceNotFoundException("Food Item Not Found with id: " + foodItemId));
        cart.getItems().remove(existingItem);
        cartRepository.save(cart);

         return mapToCartResponseDTO(cart);

    }

    @Override
    public void clearCart(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer Not Found with id: " + customerId));
        Cart cart = customer.getCart();
        if(cart==null)
        {
            throw new ResourceNotFoundException("Cart not found with id " + customerId);
        }
        cart.getItems().clear();
        cartRepository.save(cart);

    }
}
