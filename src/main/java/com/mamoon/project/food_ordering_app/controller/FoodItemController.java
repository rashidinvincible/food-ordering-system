package com.mamoon.project.food_ordering_app.controller;


import com.mamoon.project.food_ordering_app.dto.FoodItemRequestDTO;
import com.mamoon.project.food_ordering_app.dto.FoodItemResponseDto;
import com.mamoon.project.food_ordering_app.repository.FoodItemRepository;
import com.mamoon.project.food_ordering_app.services.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-items")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @GetMapping("/{foodItemId}")
    public ResponseEntity<FoodItemResponseDto> getFoodItemById(@PathVariable Long foodItemId)
    {
        return ResponseEntity.ok(foodItemService.getFoodItemById(foodItemId));
    }

    @GetMapping
    public ResponseEntity<List<FoodItemResponseDto>> getAllFoodItems()
    {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @PostMapping
    public ResponseEntity<FoodItemResponseDto>  createFoodItem(@RequestBody FoodItemRequestDTO foodItemRequestDTO)
    {
        FoodItemResponseDto foodItemResponseDto = foodItemService.createFoodItem(foodItemRequestDTO);
        return new ResponseEntity<>(foodItemResponseDto, HttpStatus.CREATED);

    }

    @PutMapping("/{foodItemId}")
    public ResponseEntity<FoodItemResponseDto> updateFoodItemById(@PathVariable Long foodItemId, @RequestBody FoodItemRequestDTO foodItemRequestDTO)
    {
        return ResponseEntity.ok(foodItemService.updateFoodItemById(foodItemId, foodItemRequestDTO));
    }

    @DeleteMapping("/{foodItemId}")
    public ResponseEntity<Void> deleteFoodItemById(@PathVariable Long foodItemId)
    {
        foodItemService.deleteFoodItemById(foodItemId);
        return ResponseEntity.noContent().build();
    }

}
