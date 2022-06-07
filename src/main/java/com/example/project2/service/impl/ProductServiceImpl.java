package com.example.project2.service.impl;

import com.example.project2.dto.ProductDto;
import com.example.project2.entity.Product;
import com.example.project2.exception.ResourceNotFoundException;
import com.example.project2.repository.ProductRepository;
import com.example.project2.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //create a product
    @Override
    public ProductDto CreateProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product newProduct = productRepository.save(product);
        ProductDto productResponse = mapToDTO(newProduct);
        return productResponse;
    }

    //return a list of all products in database
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
    }

    //update a product name
    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        product.setName(productDto.getName());

        Product updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    //delete a product based on Id
    @Override
    public void deleteProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }

    private ProductDto mapToDTO(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setSlug(product.getSlug());
        productDto.setName(product.getName());
        productDto.setReference(product.getReference());
        productDto.setPrice(product.getPrice());
        productDto.setVat(product.getVat());
        productDto.setStockable(product.getStockable());
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setReference(productDto.getReference());
        product.setSlug(productDto.getSlug());
        product.setPrice(productDto.getPrice());
        product.setVat(productDto.getVat());
        product.setStockable(productDto.getStockable());
        return product;
    }

}
