package com.gangbean.josthis.domain.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class StockTrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(nullable = false)
    private LocalDateTime tradeAt;

    @Column(nullable = false)
    private BigDecimal volume;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TradeType type;

    @Builder
    public StockTrade(Stock stock, LocalDateTime tradeAt, BigDecimal volume, BigDecimal price, TradeType type) {
        this.stock = stock;
        this.tradeAt = tradeAt;
        this.volume = volume;
        this.price = price;
        this.type = type;
    }
}
