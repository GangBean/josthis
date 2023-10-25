package com.gangbean.josthiscore.repository;

import com.gangbean.josthiscore.domain.stock.StockTrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StockTradeRepository {
    private final EntityManager em;

    public Long save(StockTrade stockTrade) {
        em.persist(stockTrade);
        return stockTrade.getId();
    }

    public List<StockTrade> findAllTradesOfStockId(Long stockId) {
        return em.createQuery("select t from StockTrade t right outer join fetch t.stock s where s.id = :stockId", StockTrade.class)
                .setParameter("stockId", stockId)
                .getResultList();
    }

    public StockTrade findById(Long tradeId) {
        return em.find(StockTrade.class, tradeId);
    }
}
