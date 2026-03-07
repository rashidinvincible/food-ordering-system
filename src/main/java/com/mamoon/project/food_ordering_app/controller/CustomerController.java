package com.mamoon.project.food_ordering_app.controller;


import com.mamoon.project.food_ordering_app.dto.CustomerRequestDTO;
import com.mamoon.project.food_ordering_app.dto.CustomerResponseDTO;
import com.mamoon.project.food_ordering_app.repository.CustomerRepository;
import com.mamoon.project.food_ordering_app.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable("customerId") Long customerId){
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
        return new ResponseEntity<>(customerService.createCustomer(customerRequestDTO), HttpStatus.CREATED);
    }
}
