package com.gangbean.josthis.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Entity
public class Consensus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    private ConsensusType type;
}
