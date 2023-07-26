package com.gangbean.josthis.controller;

import com.gangbean.josthis.dto.StockListResponse;
import com.gangbean.josthis.service.StockService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<StockListResponse> getStockList(@RequestHeader HttpHeaders headers) {
        StockListResponse allStocks = stockService.allStocks(
                headers.getFirst(HEADER_END_SCORE), headers.getFirst(HEADER_END_ID));

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        if (allStocks.hasResponse()) {
            header.add(HEADER_END_SCORE, String.valueOf(allStocks.lastStock().getConsensusScore()));
            header.add(HEADER_END_SCORE, String.valueOf(allStocks.lastStock().getId()));
        }

        return new ResponseEntity<>(allStocks, header, HttpStatus.OK);
    }
}
