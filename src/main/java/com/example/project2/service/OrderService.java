package com.example.project2.service;

import com.example.project2.dto.OrdersDto;

import java.util.List;

public interface OrderService {

    OrdersDto createOrder(OrdersDto ordersDto);

    List<OrdersDto> getAllOrders();

    OrdersDto updateOrder(OrdersDto ordersDto, Integer id);

    void deleteOrderById(Integer id);

}
