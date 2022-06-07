package com.example.project2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {

    private Integer id;

    private String first_name;

    private String last_name;

    private Date born_at;

}
