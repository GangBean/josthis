package com.gangbean.josthis.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class BadStockStatusHolder {
    @Id
    @Enumerated(EnumType.STRING)
    private BadStockStatus status;

    public BadStockStatusHolder() {
    }

    public BadStockStatusHolder(BadStockStatus status) {
        this.status = status;
    }
}
