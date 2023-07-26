package com.gangbean.josthis.dto;

import com.gangbean.josthis.domain.Stock;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StockListResponse {

    List<StockResponse> stocks;

    public StockListResponse(List<StockResponse> stocks) {
        this.stocks = stocks;
    }

    public static StockListResponse responseFrom(List<Stock> stocks) {
        return new StockListResponse(stocks.stream()
                .map(StockResponse::responseFrom)
                .collect(Collectors.toList()));
    }

    public StockResponse lastStock() {
        return stocks.isEmpty() ? null : stocks.get(stocks.size() - 1);
    }

    public boolean hasResponse() {
        return !stocks.isEmpty();
    }
}
