package com.mamoon.project.food_ordering_app.repository;

import com.mamoon.project.food_ordering_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
