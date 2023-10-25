package com.gangbean.josthisapi.controller;

import com.gangbean.josthisapi.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // 요청을 허용할 도메인을 설정
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @PostMapping("/new")
    public ResponseEntity<StockForm> registerNewStock(@RequestBody StockForm form) {
        Long registeredId = stockService.register(form);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(String.format("/api/stocks/%d", registeredId)));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<List<StockTradeForm>> tradesOfStock(@PathVariable Long stockId) {
        return ResponseEntity.ok(stockService.tradesOfStockId(stockId));
    }

    @PostMapping("/{stockId}/trades/new")
    public ResponseEntity<StockTradeForm> writeNewTrade(@PathVariable Long stockId, @RequestBody StockTradeForm form) {
        stockService.write(stockId, form);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(String.format("/api/stocks/%d", stockId)));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    @GetMapping
    public ResponseEntity<List<StockForm>> stocks() {
        return ResponseEntity.ok(stockService.listOfStocks());
    }
}
