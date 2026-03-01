package com.mamoon.project.food_ordering_app.repository;

import com.mamoon.project.food_ordering_app.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments, Long> {
}
