package com.gangbean.josthis.domain;

import java.io.IOException;

@FunctionalInterface
public interface StockFetchSource {
    Stock fetch(String tickerCode) throws IOException;
}
