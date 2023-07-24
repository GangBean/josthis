package com.gangbean.josthis.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Stock extends Reportable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tickerCode;

    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StockHistory> histories;

    private BigDecimal per;

    private BigDecimal eps;

    private BigDecimal dividend;

    private BigDecimal listedVolume;

    @Enumerated(EnumType.STRING)
    private StockStatus status;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Consensus consensus;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private JosthisScore josthisScore;

}
