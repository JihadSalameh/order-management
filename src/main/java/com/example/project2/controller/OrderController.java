package com.example.project2.controller;

import com.example.project2.dto.OrdersDto;
import com.example.project2.exception.BadRequestException;
import com.example.project2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<OrdersDto> createOrder(@Valid @RequestBody OrdersDto ordersDto) {
        if (ordersDto.getId() != null) {
            log.error("Cannot have an ID {}", ordersDto);
            throw new BadRequestException(OrderController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity(orderService.createOrder(ordersDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDto> updateOrder(@Valid @RequestBody OrdersDto ordersDto, @PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(orderService.updateOrder(ordersDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable(name = "id") Integer id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

}
