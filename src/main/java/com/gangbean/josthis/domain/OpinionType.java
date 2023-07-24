package com.gangbean.josthis.domain;

public enum OpinionType {
    UP("상향"),
    MIDDLE("유지"),
    DOWN("하향");

    private final String kor;

    OpinionType(String kor) {
        this.kor = kor;
    }

    public String getKor() {
        return kor;
    }
}
