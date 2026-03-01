package com.mamoon.project.food_ordering_app.entity;

import com.mamoon.project.food_ordering_app.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private Role role;

    @OneToOne(mappedBy = "customer" , cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    List<Order> orders = new ArrayList<>();



}
