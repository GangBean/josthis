package com.gangbean.josthis.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Embeddable
public class Consensus {

    @ColumnDefault("0")
    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    private ConsensusType type;

    public Consensus() {}

    public Consensus(BigDecimal score, ConsensusType type) {
        this.score = score;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consensus consensus = (Consensus) o;
        return Objects.equals(score, consensus.score) && type == consensus.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, type);
    }
}
