package com.example.project2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrdersDto {

    private Integer id;

    private String customer_id;

    private Date ordered_at;

}
