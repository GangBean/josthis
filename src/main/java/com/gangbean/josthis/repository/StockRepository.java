package com.gangbean.josthis.repository;

import com.gangbean.josthis.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findTop20ByOrderByConsensusScore();
}
