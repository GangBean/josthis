package com.gangbean.josthis.domain;

import com.gangbean.josthis.exception.HankyungNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

@Configuration
public class HanKyungSource implements StockFetchSource {
    public static final String HANKYUNG_STOCK_BASE_URL = "https://markets.hankyung.com/stock/";

    @Override
    public Stock fetch(String tickerCode) throws IOException {
        return stockFrom(getFromUrl(fullUrlOf(tickerCode)));
    }

    private String fullUrlOf(String tickerCode) {
        return HANKYUNG_STOCK_BASE_URL + tickerCode;
    }

    private Document getFromUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    private Stock stockFrom(Document document) {
        String stockName = document.getElementsByClass("stock-name").get(0).text();
        Elements priceElement = document.getElementsByClass("stock-data txt-num up");
        if (priceElement.isEmpty()) {
            throw new HankyungNotFoundException("한경 가격 정보를 찾을 수 없습니다: " + stockName);
        }
        Elements consensusNumberElement = document.getElementsByClass("consensus-scale step4");
        if (consensusNumberElement.isEmpty()) {
            throw new HankyungNotFoundException("한경 컨센서스 정보를 찾을 수 없습니다: " + stockName);
        }
        String consensusScore = consensusNumberElement.get(0).getElementsByClass("txt-num").get(0).text();
        String consensusType = consensusNumberElement.get(0).getElementsByClass("item on").get(0).text().trim();

        return Stock.builder()
                .name(stockName)
                .price(new BigDecimal(priceElement.get(0).getElementsByClass("price").get(0).text().replaceAll(",", "")))
                .per(BigDecimal.ZERO)
                .eps(BigDecimal.ZERO)
                .dividend(BigDecimal.ZERO)
                .listedVolume(BigDecimal.ZERO)
                .badStatus(Set.of())
                .consensus(new Consensus(new BigDecimal(consensusScore), ConsensusType.of(consensusType)))
                .josthisScore(new JosthisScore(new BigDecimal("0")))
                .histories(Set.of(StockHistory.builder()
                        .price(new BigDecimal(priceElement.get(0).getElementsByClass("price").get(0).text().replaceAll(",", "")))
                        .per(BigDecimal.ZERO)
                        .eps(BigDecimal.ZERO)
                        .dividend(BigDecimal.ZERO)
                        .listedVolume(BigDecimal.ZERO)
                        .badStatus(Set.of())
                        .consensus(new Consensus(new BigDecimal(consensusScore), ConsensusType.of(consensusType)))
                        .josthisScore(new JosthisScore(new BigDecimal("0")))
                        .build()))
                .build();
    }
}
