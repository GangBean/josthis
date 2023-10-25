package com.gangbean.josthiscore.domain.stock;

public enum TradeType {
    BUY("매수"), SELL("매도");
    TradeType(String kor) {
        this.kor = kor;
    }
    private String kor;
}
