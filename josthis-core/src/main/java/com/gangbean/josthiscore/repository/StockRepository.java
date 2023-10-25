package com.gangbean.josthiscore.repository;

import com.gangbean.josthiscore.domain.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StockRepository {
    private final EntityManager em;

    public Long save(Stock stock) {
        em.persist(stock.getLogo());
        em.persist(stock);
        return stock.getId();
    }

    public Stock findById(Long id) {
        return em.find(Stock.class, id);
    }

    public List<Stock> findAll() {
        return em.createQuery("select s from Stock s join fetch s.logo l", Stock.class)
                .getResultList();
    }
}
