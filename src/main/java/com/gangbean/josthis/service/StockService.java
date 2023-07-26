package com.gangbean.josthis.service;

import com.gangbean.josthis.dto.StockListResponse;
import com.gangbean.josthis.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Transactional(readOnly = true)
@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockListResponse allStocks(String prevScore, String prevId) {
        return StockListResponse.responseFrom(
                (!StringUtils.hasText(prevScore) || !StringUtils.hasText(prevId)) ?
                        stockRepository.findTop20ByOrderByConsensusScoreAscIdAsc() :
                        stockRepository.findTop20ByConsensusScoreAndIdGreaterThanOrConsensusScoreGreaterThanOrderByConsensusScoreAscIdAsc(
                                new BigDecimal(prevScore), Long.parseLong(prevId), new BigDecimal(prevScore)));
    }
}
