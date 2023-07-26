package com.gangbean.josthis.controller;

import com.gangbean.josthis.dto.StockListResponse;
import com.gangbean.josthis.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class StockController {

    public static final String HEADER_END_SCORE = "EndScore";
    public static final String HEADER_END_ID = "EndId";
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks")
    public ResponseEntity<StockListResponse> getStockList(
            @RequestParam(value = "EndScore", required = false) BigDecimal endScore,
            @RequestParam(value = "EndId", required = false) Long endId) {
        StockListResponse allStocks = stockService.allStocks(endScore, endId);

        return ResponseEntity.ok(allStocks);
    }
}
