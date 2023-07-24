package com.gangbean.josthis.service;

import com.gangbean.josthis.dto.StockListResponse;
import com.gangbean.josthis.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockListResponse allStocks() {
        return StockListResponse.responseFrom(stockRepository.findTop20ByOrderByConsensusScore());
    }
}
