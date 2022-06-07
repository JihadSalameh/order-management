package com.example.project2.controller;

import com.example.project2.dto.Product_OrderDto;
import com.example.project2.exception.BadRequestException;
import com.example.project2.service.Product_OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product_order")
public class Product_OrderController {

    private final Logger log = LoggerFactory.getLogger(Product_OrderController.class);

    private Product_OrderService product_orderService;

    public Product_OrderController(Product_OrderService product_orderService) {
        this.product_orderService = product_orderService;
    }

    @GetMapping
    public ResponseEntity<List<Product_OrderDto>> getAllOrders() {
        return ResponseEntity.ok().body(product_orderService.getAllProduct_Orders());
    }

    @PostMapping
    public ResponseEntity<Product_OrderDto> createProduct_Order(@Valid @RequestBody Product_OrderDto product_orderDto) {
        if (product_orderDto.getId() != null) {
            log.error("Cannot have an ID {}", product_orderDto);
            throw new BadRequestException(Product_OrderController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity(product_orderService.createProduct_Order(product_orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product_OrderDto> updateProduct_Order(@Valid @RequestBody Product_OrderDto product_orderDto, @PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(product_orderService.updateProduct_Order(product_orderDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct_OrderById(@PathVariable(name = "id") Integer id) {
        product_orderService.deleteProduct_OrderById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

}
