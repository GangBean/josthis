package com.gangbean.josthis.dto;

import com.gangbean.josthis.domain.ConsensusType;
import com.gangbean.josthis.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@Getter
public class StockResponse {

    private Long id;

    private String name;

    private String tickerCode;

    private BigDecimal price;

    private BigDecimal consensusScore;

    private ConsensusType consensusType;


    public static StockResponse responseFrom(Stock stock) {
        return new StockResponse(
                stock.getId(),
                stock.getName(),
                stock.getTickerCode(),
                stock.getPrice(),
                stock.getConsensus().getScore(),
                stock.getConsensus().getType()
        );
    }
}
