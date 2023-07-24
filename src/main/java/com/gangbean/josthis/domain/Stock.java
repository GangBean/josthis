package com.gangbean.josthis.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StockHistory> histories;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BadStockStatusHolder> badStatus;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Consensus consensus;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private JosthisScore josthisScore;

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
            Set<BadStockStatusHolder> badStatus,
            Consensus consensus,
            JosthisScore josthisScore) {
        this.name = name;
        this.tickerCode = tickerCode;
        this.price = price;
        this.histories = histories;
        this.per = per;
        this.eps = eps;
        this.dividend = dividend;
        this.listedVolume = listedVolume;
        this.badStatus = badStatus;
        this.consensus = consensus;
        this.josthisScore = josthisScore;
    }
}
