package com.gangbean.josthis.domain;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class JosthisScore {

    @ColumnDefault("0")
    @Column(nullable = false)
    private BigDecimal josthisScore;

    public JosthisScore(BigDecimal josthisScore) {
        this.josthisScore = josthisScore;
    }

    public JosthisScore() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JosthisScore that = (JosthisScore) o;
        return Objects.equals(josthisScore, that.josthisScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(josthisScore);
    }
}
