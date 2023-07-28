package com.gangbean.josthis.domain;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HanKyungSource implements StockFetchSource {
    @Override
    public Stock fetch(String tickerCode) {
        return null;
    }
}
