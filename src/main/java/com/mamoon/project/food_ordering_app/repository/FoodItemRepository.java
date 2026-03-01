package com.mamoon.project.food_ordering_app.repository;

import com.mamoon.project.food_ordering_app.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
