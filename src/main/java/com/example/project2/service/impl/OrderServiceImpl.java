package com.example.project2.service.impl;

import com.example.project2.dto.OrdersDto;
import com.example.project2.entity.Order;
import com.example.project2.entity.Product;
import com.example.project2.exception.ResourceNotFoundException;
import com.example.project2.repository.OrdersRepository;
import com.example.project2.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrdersRepository ordersRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    //create a order
    @Override
    public OrdersDto createOrder(OrdersDto ordersDto) {
        Order order = mapToEntity(ordersDto);
        Order newOrder = ordersRepository.save(order);
        OrdersDto OrderResponse = mapToDTO(newOrder);
        return OrderResponse;
    }

    //return all orders from the database to a list
    @Override
    public List<OrdersDto> getAllOrders() {
        List<Order> orders = ordersRepository.findAll();
        return orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
    }

    //update a order date
    @Override
    public OrdersDto updateOrder(OrdersDto ordersDto, Integer id) {
        Order order = ordersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));

        order.setOrdered_at(ordersDto.getOrdered_at());

        Order updatedOrder = ordersRepository.save(order);
        return mapToDTO(updatedOrder);
    }

    //delete a order in the database based on Id
    @Override
    public void deleteOrderById(Integer id) {
        Order order = ordersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        ordersRepository.delete(order);
    }

    private OrdersDto mapToDTO(Order order) {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(order.getId());
        ordersDto.setCustomer_id(order.getCustomer_id());
        ordersDto.setOrdered_at(order.getOrdered_at());
        return ordersDto;
    }

    private Order mapToEntity(OrdersDto ordersDto) {
        Order order = new Order();
        order.setId(ordersDto.getId());
        order.setCustomer_id(ordersDto.getCustomer_id());
        order.setOrdered_at(ordersDto.getOrdered_at());
        return order;
    }

}
