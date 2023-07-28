package com.gangbean.josthis.domain;

@FunctionalInterface
public interface StockFetchSource {
    Stock fetch(String tickerCode);
}
