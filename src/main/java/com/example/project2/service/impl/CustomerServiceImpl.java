package com.example.project2.service.impl;

import com.example.project2.dto.CustomerDto;
import com.example.project2.entity.Customer;
import com.example.project2.entity.Product;
import com.example.project2.exception.ResourceNotFoundException;
import com.example.project2.repository.CustomerRepository;
import com.example.project2.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //create a customer
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer newCustomer = customerRepository.save(customer);
        CustomerDto CustomerResponse = mapToDTO(newCustomer);
        return CustomerResponse;
    }

    //returns a list of customers
    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapToDTO(customer)).collect(Collectors.toList());
    }

    //update the customers first name
    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customer.setFirst_name(customerDto.getFirst_name());

        Customer updatedCustomer = customerRepository.save(customer);
        return mapToDTO(updatedCustomer);
    }

    //delete a customer from the database based on their Id
    @Override
    public void deleteCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(customer);
    }

    private CustomerDto mapToDTO(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirst_name(customer.getFirst_name());
        customerDto.setLast_name(customer.getLast_name());
        customerDto.setBorn_at(customer.getBorn_at());
        return customerDto;
    }

    private Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirst_name(customerDto.getFirst_name());
        customer.setLast_name(customerDto.getLast_name());
        customer.setBorn_at(customerDto.getBorn_at());
        return customer;
    }

}
