package com.example.project2.service;

import com.example.project2.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(CustomerDto customerDto, Integer id);

    void deleteCustomerById(Integer id);

}
