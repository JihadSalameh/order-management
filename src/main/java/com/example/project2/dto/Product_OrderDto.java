package com.example.project2.dto;

import lombok.Data;

@Data
public class Product_OrderDto {

    private Integer id;

    private Integer product_id;

    private Integer order_id;

    private Integer quantity;

    private Double price;

    private Double vat;

}
