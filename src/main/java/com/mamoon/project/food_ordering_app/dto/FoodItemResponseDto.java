package com.mamoon.project.food_ordering_app.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
public class FoodItemResponseDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String category;
    private String description;
    private Boolean available;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FoodItemResponseDto that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(category, that.category) && Objects.equals(description, that.description) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, description, available);
    }

    @Override
    public String toString() {
        return "FoodItemResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
