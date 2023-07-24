package com.gangbean.josthis.domain;


public enum BadStockStatus {
    SUSPENSION("매매거래정지"),
    CLEARANCE_SALE("정리매매"),
    IN_MANAGEMENT("관리종목"),
    ALERT("투자주의환기"),
    UNFAITHFUL_DISCLOSURE("불성실공시"),
    LISTED_SHARES_SHORTAGE("상장주식수부족"),
    SHORT_TERM_OVERHEATING("단기과열"),
    CAUTION("투자주의"),
    WARNING("투자경고"),
    RISKY("투자위험");

    private final String kor;

    BadStockStatus(String kor) {
        this.kor = kor;
    }

    public String getKor() {
        return kor;
    }
}
