package com.example.project2.controller;

import com.example.project2.dto.StockDto;
import com.example.project2.exception.BadRequestException;
import com.example.project2.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final Logger log = LoggerFactory.getLogger(StockController.class);

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks() {
        return ResponseEntity.ok().body(stockService.getAllStocks());
    }

    @PostMapping
    public ResponseEntity<StockDto> createStock(@Valid @RequestBody StockDto stockDto) {
        if (stockDto.getId() != null) {
            log.error("Cannot have an ID {}", stockDto);
            throw new BadRequestException(StockController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity(stockService.createStock(stockDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDto> updateStock(@Valid @RequestBody StockDto stockDto, @PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(stockService.updateStock(stockDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStockById(@PathVariable(name = "id") Integer id) {
        stockService.deleteStockById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

}
