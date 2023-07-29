package com.gangbean.josthis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public StockHistory(BigDecimal price, BigDecimal per, BigDecimal eps, BigDecimal dividend, BigDecimal listedVolume, Set<BadStockStatus> badStatus, Consensus consensus, JosthisScore josthisScore) {
        this.price = price;
        this.per = per;
        this.eps = eps;
        this.dividend = dividend;
        this.listedVolume = listedVolume;
        this.badStatus = badStatus;
        this.consensus = consensus;
        this.josthisScore = josthisScore;
    }

    public static StockHistory of(Stock stock) {
        return new StockHistoryBuilder()
                .price(stock.getPrice())
                .per(stock.getPer())
                .eps(stock.getEps())
                .dividend(stock.getDividend())
                .listedVolume(stock.getListedVolume())
                .badStatus(stock.getBadStatus())
                .consensus(stock.getConsensus())
                .josthisScore(stock.getJosthisScore())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockHistory that = (StockHistory) o;
        return Objects.equals(price, that.price) && Objects.equals(per, that.per) && Objects.equals(eps, that.eps) && Objects.equals(dividend, that.dividend) && Objects.equals(listedVolume, that.listedVolume) && Objects.equals(badStatus, that.badStatus) && Objects.equals(consensus, that.consensus) && Objects.equals(josthisScore, that.josthisScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, per, eps, dividend, listedVolume, badStatus, consensus, josthisScore);
    }
}
