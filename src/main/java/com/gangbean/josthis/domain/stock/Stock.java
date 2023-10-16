package com.gangbean.josthis.domain.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 6, unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal marketCap;

    @Column(nullable = false)
    private BigDecimal issuedAmount;

    @Column(nullable = false)
    private BigDecimal eps;

    @Column(nullable = false)
    private BigDecimal per;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockStatus status;

    @Builder
    public Stock(String name, String code, BigDecimal price, BigDecimal marketCap, BigDecimal issuedAmount, BigDecimal eps, BigDecimal per, StockStatus status) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.marketCap = marketCap;
        this.issuedAmount = issuedAmount;
        this.eps = eps;
        this.per = per;
        this.status = status;
    }
}
