package com.mamoon.project.food_ordering_app.entity;

import com.mamoon.project.food_ordering_app.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private PaymentStatus  paymentStatus;
    private LocalDate paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
