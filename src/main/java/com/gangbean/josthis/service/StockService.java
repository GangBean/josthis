package com.gangbean.josthis.service;

import com.gangbean.josthis.dto.StockListResponse;
import com.gangbean.josthis.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional(readOnly = true)
@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockListResponse allStocks(BigDecimal prevScore, Long prevId) {
        return StockListResponse.responseFrom(
                (prevScore == null || prevId == null) ?
                        stockRepository.findTop20ByOrderByConsensusScoreAscIdAsc() :
                        stockRepository.findTop20ByConsensusScoreAndIdGreaterThanOrConsensusScoreGreaterThanOrderByConsensusScoreAscIdAsc(
                                prevScore, prevId, prevScore));
    }
}
