package com.example.project2.service.impl;

import com.example.project2.dto.StockDto;
import com.example.project2.entity.Product;
import com.example.project2.entity.Stock;
import com.example.project2.exception.ResourceNotFoundException;
import com.example.project2.repository.StockRepository;
import com.example.project2.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockSerivceImpl implements StockService {

    private StockRepository stockRepository;

    public StockSerivceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    //this function creates a stock
    @Override
    public StockDto createStock(StockDto stockDto) {
        Stock stock = mapToEntity(stockDto);
        Stock newStock = stockRepository.save(stock);
        StockDto stockResponse = mapToDTO(newStock);
        return stockResponse;
    }

    //returns all stocks in database to a list of StockDto
    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(stock -> mapToDTO(stock)).collect(Collectors.toList());
    }


    //updates quantity in a stock
    @Override
    public StockDto updateStock(StockDto stockDto, Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));

        stock.setQuantity(stockDto.getQuantity());

        Stock updatedStock = stockRepository.save(stock);
        return mapToDTO(updatedStock);
    }

    //delete a stock based on Id
    @Override
    public void deleteStockById(Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
        stockRepository.delete(stock);
    }

    private StockDto mapToDTO(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setProductId(stock.getProductId());
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setUpdatedAt(stock.getUpdatedAt());
        return stockDto;
    }

    private Stock mapToEntity(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setId(stockDto.getId());
        stock.setProductId(stockDto.getProductId());
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdatedAt(stockDto.getUpdatedAt());
        return stock;
    }

}
