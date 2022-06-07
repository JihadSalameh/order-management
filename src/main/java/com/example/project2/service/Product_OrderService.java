package com.example.project2.service;

import com.example.project2.dto.Product_OrderDto;

import java.util.List;

public interface Product_OrderService {

    Product_OrderDto createProduct_Order(Product_OrderDto product_orderDto);

    List<Product_OrderDto> getAllProduct_Orders();

    Product_OrderDto updateProduct_Order(Product_OrderDto product_orderDto, Integer id);

    void deleteProduct_OrderById(Integer id);

}
