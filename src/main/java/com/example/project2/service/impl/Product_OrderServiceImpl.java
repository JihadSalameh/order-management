package com.example.project2.service.impl;

import com.example.project2.dto.Product_OrderDto;
import com.example.project2.entity.Product;
import com.example.project2.entity.Product_Order;
import com.example.project2.exception.ResourceNotFoundException;
import com.example.project2.repository.Product_OrderRepository;
import com.example.project2.service.Product_OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Product_OrderServiceImpl implements Product_OrderService {

    private Product_OrderRepository Product_OrderRepository;

    public Product_OrderServiceImpl(Product_OrderRepository Product_OrderRepository) {
        this.Product_OrderRepository = Product_OrderRepository;
    }

    //create a record
    @Override
    public Product_OrderDto createProduct_Order(Product_OrderDto product_orderDto) {
        Product_Order product_order = mapToEntity(product_orderDto);
        Product_Order newProduct_Order = Product_OrderRepository.save(product_order);
        Product_OrderDto product_OrderResponse = mapToDTO(newProduct_Order);
        return product_OrderResponse;
    }

    //get all records from database
    @Override
    public List<Product_OrderDto> getAllProduct_Orders() {
        List<Product_Order> product_orders = Product_OrderRepository.findAll();
        return product_orders.stream().map(po -> mapToDTO(po)).collect(Collectors.toList());
    }

    //update a record price
    @Override
    public Product_OrderDto updateProduct_Order(Product_OrderDto product_orderDto, Integer id) {
        Product_Order product_order = Product_OrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product_Order", "id", id));

        product_order.setPrice(product_orderDto.getPrice());

        Product_Order updatedProduct_Order = Product_OrderRepository.save(product_order);
        return mapToDTO(updatedProduct_Order);
    }

    //delete a record using its Id
    @Override
    public void deleteProduct_OrderById(Integer id) {
        Product_Order product_order = Product_OrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product_Order", "id", id));
        Product_OrderRepository.delete(product_order);
    }

    private Product_OrderDto mapToDTO(Product_Order po) {
        Product_OrderDto product_orderDto = new Product_OrderDto();
        product_orderDto.setId(po.getId());
        product_orderDto.setOrder_id(po.getOrder_id());
        product_orderDto.setProduct_id(po.getProduct_id());
        product_orderDto.setQuantity(po.getQuantity());
        product_orderDto.setPrice(po.getPrice());
        product_orderDto.setVat(po.getVat());
        return product_orderDto;
    }

    private Product_Order mapToEntity(Product_OrderDto product_orderDto) {
        Product_Order product_order = new Product_Order();
        product_order.setOrder_id(product_orderDto.getOrder_id());
        product_order.setProduct_id(product_orderDto.getProduct_id());
        product_order.setId(product_orderDto.getId());
        product_order.setQuantity(product_orderDto.getQuantity());
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setVat(product_orderDto.getVat());
        return product_order;
    }

}
