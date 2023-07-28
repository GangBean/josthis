package com.gangbean.josthis.domain;

import com.gangbean.josthis.exception.StockNotContainsInitialStateHistoryException;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 14, max = 14)
    @Column(unique = true, nullable = false, updatable = false)
    private String tickerCode;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("0")
    private BigDecimal price;

    @Column(nullable = false)
    @ColumnDefault("0")
    private BigDecimal per;

    @Column(nullable = false)
    @ColumnDefault("0")
    private BigDecimal eps;

    @Column(nullable = false)
    @ColumnDefault("0")
    private BigDecimal dividend;

    @Column(nullable = false)
    @ColumnDefault("0")
    private BigDecimal listedVolume;

    @ElementCollection(targetClass = BadStockStatus.class)
    private Set<BadStockStatus> badStatus;

    @Embedded
    private Consensus consensus;

    @Embedded
    private JosthisScore josthisScore;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StockHistory> histories;

    public Stock() {}

    @Builder
    public Stock(
            String name,
            String tickerCode,
            BigDecimal price,
            Set<StockHistory> histories,
            BigDecimal per,
            BigDecimal eps,
            BigDecimal dividend,
            BigDecimal listedVolume,
            Set<BadStockStatus> badStatus,
            Consensus consensus,
            JosthisScore josthisScore) {
        this.name = name;
        this.tickerCode = tickerCode;
        this.price = price;
        this.per = per;
        this.eps = eps;
        this.dividend = dividend;
        this.listedVolume = listedVolume;
        this.badStatus = badStatus;
        this.consensus = consensus;
        this.josthisScore = josthisScore;
        this.histories = containsCurrent(histories);
    }

    private Set<StockHistory> containsCurrent(Set<StockHistory> histories) {
        if (histories == null || !histories.contains(StockHistory.of(this))) {
            throw new StockNotContainsInitialStateHistoryException("주식은 최초상태의 이력을 가져야 합니다.");
        }
        return histories;
    }

    public Stock update(StockFetchSource source) {
        this.merge(source.fetch(tickerCode));
        return this;
    }

    private void merge(Stock updatableStock) {
        boolean isOutdated = false;
        if (!Objects.equals(updatableStock.price, this.price)) {
            this.price = updatableStock.price;
            isOutdated = true;
        }

        if (!Objects.equals(updatableStock.per, this.per)) {
            this.per = updatableStock.per;
            isOutdated = true;
        }

        if (!Objects.equals(updatableStock.eps, this.eps)) {
            this.eps = updatableStock.eps;
            isOutdated = true;
        }

        if (!Objects.equals(updatableStock.dividend, this.dividend)) {
            this.dividend = updatableStock.dividend;
            isOutdated = true;
        }

        if (!Objects.equals(updatableStock.listedVolume, this.listedVolume)) {
            this.listedVolume = updatableStock.listedVolume;
            isOutdated = true;
        }

        if (!Objects.equals(updatableStock.badStatus, this.badStatus)) {
            this.badStatus = updatableStock.badStatus;
            isOutdated = true;
        }

        if (!Objects.equals(updatableStock.consensus, this.consensus)) {
            this.consensus = updatableStock.consensus;
            isOutdated = true;
        }

        if (isOutdated) {
            this.histories.addAll(updatableStock.histories);
        }
    }
}
