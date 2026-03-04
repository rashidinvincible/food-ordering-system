package com.mamoon.project.food_ordering_app.services;

import com.mamoon.project.food_ordering_app.dto.CustomerRequestDTO;
import com.mamoon.project.food_ordering_app.dto.CustomerResponseDTO;
import com.mamoon.project.food_ordering_app.entity.Customer;
import com.mamoon.project.food_ordering_app.exception.ResourceNotFoundException;
import com.mamoon.project.food_ordering_app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = modelMapper.map(customerRequestDTO, Customer.class);
        customer=customerRepository.save(customer);
        return modelMapper.map(customer,CustomerResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found with id " + id));
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }
}
