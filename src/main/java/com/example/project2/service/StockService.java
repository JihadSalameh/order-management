package com.example.project2.service;

import com.example.project2.dto.StockDto;

import java.util.List;

public interface StockService {

    StockDto createStock(StockDto stockDto);

    List<StockDto> getAllStocks();

    StockDto updateStock(StockDto stockDto, Integer id);

    void deleteStockById(Integer id);

}
