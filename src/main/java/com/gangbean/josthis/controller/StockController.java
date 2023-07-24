package com.gangbean.josthis.controller;

import com.gangbean.josthis.dto.StockListResponse;
import com.gangbean.josthis.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks")
    public ResponseEntity<StockListResponse> getStockList() {
        return ResponseEntity.ok(stockService.allStocks());
    }
}
