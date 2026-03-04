package com.mamoon.project.food_ordering_app.services;

import com.mamoon.project.food_ordering_app.dto.CustomerRequestDTO;
import com.mamoon.project.food_ordering_app.dto.CustomerResponseDTO;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO getCustomerById(Long id);

}
