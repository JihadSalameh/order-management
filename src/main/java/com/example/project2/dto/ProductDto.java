package com.example.project2.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Integer id;

    private String slug;

    private String name;

    private String reference;

    private Double price;

    private Double vat;

    private Boolean stockable;

}
