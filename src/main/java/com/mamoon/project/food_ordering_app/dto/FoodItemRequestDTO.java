package com.mamoon.project.food_ordering_app.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter

public class FoodItemRequestDTO {


        @NotBlank(message="Food name cannot be empty")
        private String name;
        @NotNull(message="Price is required")
        @DecimalMin(value="0.0", inclusive=false, message="Price must be greater than 0")
        private BigDecimal price;
        private String category;
        private String description;
        private Boolean available;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FoodItemRequestDTO that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getCategory(), that.getCategory()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getAvailable(), that.getAvailable());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getCategory(), getDescription(), getAvailable());
    }

    @Override
    public String toString() {
        return "FoodItemRequestDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
