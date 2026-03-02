package com.mamoon.project.food_ordering_app.services;

import com.mamoon.project.food_ordering_app.dto.FoodItemRequestDTO;
import com.mamoon.project.food_ordering_app.dto.FoodItemResponseDto;
import com.mamoon.project.food_ordering_app.entity.FoodItem;
import com.mamoon.project.food_ordering_app.exception.ResourceNotFoundException;
import com.mamoon.project.food_ordering_app.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final ModelMapper modelMapper;


    @Override
    public FoodItemResponseDto createFoodItem(FoodItemRequestDTO foodItemRequestDTO) {
        FoodItem foodItem = modelMapper.map(foodItemRequestDTO, FoodItem.class);
        foodItem =  foodItemRepository.save(foodItem);
        return modelMapper.map(foodItem, FoodItemResponseDto.class);

    }

    @Override
    public List<FoodItemResponseDto> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        return foodItems.stream().map(foodItem -> modelMapper.map(foodItem,FoodItemResponseDto.class)).toList();
    }

    @Override
    public FoodItemResponseDto getFoodItemById(Long foodItemId) {
        FoodItem foodItem = foodItemRepository.findById(foodItemId).orElseThrow(()-> new ResourceNotFoundException("Food Item Not Found with id: " + foodItemId));
        return modelMapper.map(foodItem, FoodItemResponseDto.class);
    }

    @Override
    public FoodItemResponseDto updateFoodItemById(Long foodItemId, FoodItemRequestDTO foodItemRequestDTO) {
        FoodItem foodItem = foodItemRepository.findById(foodItemId).orElseThrow(()-> new ResourceNotFoundException("Food Item Not Found with id: " + foodItemId));
        modelMapper.map(foodItemRequestDTO, foodItem);
        foodItem.setId(foodItemId);
        foodItem = foodItemRepository.save(foodItem);
        return modelMapper.map(foodItem, FoodItemResponseDto.class);

    }

    @Override
    @Transactional
    public void deleteFoodItemById(Long foodItemId) {

        FoodItem foodItem = foodItemRepository.findById(foodItemId).orElseThrow(()-> new ResourceNotFoundException("Food Item Not Found with id: " + foodItemId));
        foodItemRepository.delete(foodItem);

    }
}
