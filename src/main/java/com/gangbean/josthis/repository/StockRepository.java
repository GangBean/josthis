package com.gangbean.josthis.repository;

import com.gangbean.josthis.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findTop20ByOrderByConsensusScoreAscIdAsc();

    List<Stock> findTop20ByConsensusScoreAndIdGreaterThanOrConsensusScoreGreaterThanOrderByConsensusScoreAscIdAsc
            (BigDecimal prevScoreFirst, Long prevId, BigDecimal samePrevScore);
}
