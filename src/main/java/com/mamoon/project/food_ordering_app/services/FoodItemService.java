package com.mamoon.project.food_ordering_app.services;

import com.mamoon.project.food_ordering_app.dto.FoodItemRequestDTO;
import com.mamoon.project.food_ordering_app.dto.FoodItemResponseDto;

import java.util.List;

public interface FoodItemService {

    public FoodItemResponseDto createFoodItem(FoodItemRequestDTO foodItemRequestDTO);

    public List<FoodItemResponseDto> getAllFoodItems();

    public FoodItemResponseDto getFoodItemById(Long foodItemId);

    public FoodItemResponseDto updateFoodItemById(Long foodItemId, FoodItemRequestDTO foodItemRequestDTO);

    public void deleteFoodItemById(Long foodItemId);
}
