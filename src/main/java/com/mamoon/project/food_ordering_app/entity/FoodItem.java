package com.mamoon.project.food_ordering_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private String category;
    private String description;
    private boolean available;

    @OneToMany(mappedBy = "foodItem")
    private List<CartItem> cartItems = new ArrayList<>();

}
