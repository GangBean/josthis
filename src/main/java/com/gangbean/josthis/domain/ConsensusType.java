package com.gangbean.josthis.domain;

import com.gangbean.josthis.exception.ConsensusTypeNotFoundException;

import java.util.Arrays;
import java.util.Objects;

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

    public static ConsensusType of(String kor) {
        return Arrays.stream(ConsensusType.values())
                .filter(t -> Objects.equals(t.kor, kor))
                .findAny()
                .orElseThrow(() -> new ConsensusTypeNotFoundException("일치하는 컨센서스 타입이 존재하지 않습니다: " + kor));
    }
}
