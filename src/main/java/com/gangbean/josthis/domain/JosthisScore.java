package com.gangbean.josthis.domain;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class JosthisScore extends BaseEntity {

    @ColumnDefault("0")
    @Column(nullable = false)
    private BigDecimal score;

    public JosthisScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JosthisScore that = (JosthisScore) o;
        return Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
