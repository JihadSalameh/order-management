package com.example.project2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StockDto {

    private Integer id;

    private Integer productId;

    private Integer quantity;

    private Date updatedAt;

}
