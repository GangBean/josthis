package com.gangbean.josthis.controller;

import com.gangbean.josthis.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @PostMapping("/new")
    public ResponseEntity<StockForm> registerNewStock(@RequestBody StockForm form) {
        Long registeredId = stockService.register(form);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(String.format("/stocks/%d", registeredId)));
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
        headers.setLocation(URI.create(String.format("/stocks/%d", stockId)));
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
