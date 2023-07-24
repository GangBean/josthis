package com.gangbean.josthis.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reportable type;

    private LocalDateTime publishingDate;

    private String title;

    private String content;

    private BigDecimal targetPrice;

    private OpinionType opinionType;
}
