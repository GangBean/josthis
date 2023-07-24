package com.gangbean.josthis.domain;

public enum ConsensusType {
    STRONG_BUY("강력매수"),
    BUY("매수"),
    POSSESSION("보유"),
    SELL("매도"),
    STRONG_SELL("강력매도");

    private final String kor;

    ConsensusType(String kor) {
        this.kor = kor;
    }

    public String getKor() {
        return kor;
    }
}
