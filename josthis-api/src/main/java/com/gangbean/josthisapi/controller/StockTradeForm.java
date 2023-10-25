package com.gangbean.josthisapi.controller;

import com.gangbean.josthiscore.domain.stock.StockTrade;
import com.gangbean.josthiscore.domain.stock.TradeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StockTradeForm {
    private Long id;
    private Long stockId;
    private LocalDateTime tradeAt;
    private BigDecimal volume;
    private BigDecimal price;
    private TradeType type;
    public static StockTradeForm formFrom(StockTrade stockTrade) {
        return StockTradeForm.builder()
                .id(stockTrade.getId())
                .stockId(stockTrade.getId())
                .tradeAt(stockTrade.getTradeAt())
                .volume(stockTrade.getVolume())
                .price(stockTrade.getPrice())
                .type(stockTrade.getType())
                .build();
    }
}
