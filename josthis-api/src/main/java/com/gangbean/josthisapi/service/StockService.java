package com.gangbean.josthisapi.service;

import com.gangbean.josthisapi.controller.StockForm;
import com.gangbean.josthisapi.controller.StockTradeForm;
import com.gangbean.josthiscore.domain.stock.Logo;
import com.gangbean.josthiscore.domain.stock.Stock;
import com.gangbean.josthiscore.domain.stock.StockStatus;
import com.gangbean.josthiscore.domain.stock.StockTrade;
import com.gangbean.josthiscore.repository.StockRepository;
import com.gangbean.josthiscore.repository.StockTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StockService {
    private final StockRepository stockRepository;
    private final StockTradeRepository stockTradeRepository;

    @Transactional
    public Long register(StockForm form) {
        return stockRepository.save(Stock.builder()
                        .name(form.getName())
                        .code(form.getCode())
                        .price(form.getPrice())
                        .eps(form.getEps())
                        .issuedAmount(form.getIssuedAmount())
                        .marketCap(form.getMarketCap())
                        .per(form.getPer())
                        .status(StockStatus.ACTIVE)
                        .logo(Logo.builder().url(form.getLogoUrl()).build())
                .build());
    }

    @Transactional
    public Long write(Long stockId, StockTradeForm form) {
        return stockTradeRepository.save(StockTrade.builder()
                        .stock(stockRepository.findById(stockId))
                        .tradeAt(form.getTradeAt())
                        .price(form.getPrice())
                        .type(form.getType())
                        .volume(form.getVolume())
                .build());
    }

    public List<StockForm> listOfStocks() {
        return stockRepository.findAll().stream()
                .map(StockForm::formFrom)
                .collect(Collectors.toList());
    }

    public List<StockTradeForm> tradesOfStockId(Long stockId) {
        return stockTradeRepository.findAllTradesOfStockId(stockId)
                .stream()
                .map(StockTradeForm::formFrom)
                .collect(Collectors.toList());
    }
}
