package com.mamoon.project.food_ordering_app.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

    @NotBlank(message="Name cannot be empty")
    private String name;

    @Email(message="Invalid email format")
    @NotBlank(message ="Email cannot be empty")
    private String email;

}
