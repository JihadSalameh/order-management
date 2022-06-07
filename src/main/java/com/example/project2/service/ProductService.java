package com.example.project2.service;

import com.example.project2.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto CreateProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(ProductDto productDto, Integer id);

    void deleteProductById(Integer id);

}
