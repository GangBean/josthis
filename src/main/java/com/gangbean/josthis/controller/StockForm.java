package com.gangbean.josthis.controller;

import com.gangbean.josthis.domain.stock.Stock;
import com.gangbean.josthis.domain.stock.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StockForm {
    private Long id;
    private String name;
    private String code;
    private BigDecimal price;
    private BigDecimal marketCap;
    private BigDecimal issuedAmount;
    private BigDecimal eps;
    private BigDecimal per;
    private StockStatus status;

    public static StockForm formFrom(Stock stock) {
        return StockForm.builder()
                .id(stock.getId())
                .name(stock.getName())
                .code(stock.getCode())
                .price(stock.getPrice())
                .marketCap(stock.getMarketCap())
                .issuedAmount(stock.getIssuedAmount())
                .eps(stock.getEps())
                .per(stock.getPer())
                .status(stock.getStatus())
                .build();
    }
}
