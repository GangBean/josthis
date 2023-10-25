package com.gangbean.josthisapi.service;

import com.gangbean.josthisapi.controller.StockForm;
import com.gangbean.josthisapi.controller.StockTradeForm;
import com.gangbean.josthiscore.domain.stock.*;
import com.gangbean.josthiscore.repository.StockRepository;
import com.gangbean.josthiscore.repository.StockTradeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@Transactional
@SpringBootTest
class StockServiceTest {

    @Autowired private StockService stockService;
    @Autowired private StockRepository stockRepository;
    @Autowired private StockTradeRepository stockTradeRepository;

    @Test
    @DisplayName("주식 거래 목록이 정상적으로 반환됩니다")
    void find_all_trades() {
        Stock xx전자 = newStock("000001");

        Long savedId = stockService.register(StockForm.formFrom(xx전자));

        LocalDateTime tradeAt = LocalDateTime.now();
        BigDecimal volume = BigDecimal.valueOf(10);
        BigDecimal price = BigDecimal.valueOf(12_000);
        StockTradeForm 매수거래 = StockTradeForm.builder()
                .type(TradeType.BUY)
                .tradeAt(tradeAt)
                .volume(volume)
                .price(price)
                .build();

        int count = 20;
        IntStream.range(0, count)
                .mapToObj(i -> 매수거래)
                .forEach(i -> stockService.write(savedId, i));

        List<StockTrade> trades = stockTradeRepository.findAllTradesOfStockId(savedId);

        assertEquals("거래의 수가 동일해야 합니다.", trades.size(), count);
    }

    @Test
    @DisplayName("주식 정보 목록이 정상적으로 반환됩니다")
    void find_all_stocks() {
        Stock xx전자 = newStock("000001");

        int count = 25;
        IntStream.range(0, count)
                .mapToObj(i -> String.format("%06d", i))
                .map(StockServiceTest::newStock)
                .map(StockForm::formFrom)
                .forEach(stockService::register);

        List<StockForm> stocks = stockService.listOfStocks();

        assertEquals("저장된 주식의 수가 동일해야 합니다", stocks.size(), count);
    }

    private static Stock newStock(String code) {
        return Stock.builder()
                .name("XX전자")
                .price(BigDecimal.valueOf(10_000))
                .issuedAmount(BigDecimal.valueOf(100_000))
                .code(code)
                .marketCap(BigDecimal.valueOf(1_000_000_000))
                .per(BigDecimal.valueOf(10))
                .eps(BigDecimal.valueOf(1000))
                .status(StockStatus.ACTIVE)
                .logo(Logo.builder().url("/").build())
                .build();
    }

    @Test
    @DisplayName("주식 거래 정보가 정상적으로 저장됩니다")
    void write_ok() {
        Stock xx전자 = newStock("000001");

        Long savedId = stockService.register(StockForm.formFrom(xx전자));

        LocalDateTime tradeAt = LocalDateTime.now();
        BigDecimal volume = BigDecimal.valueOf(10);
        BigDecimal price = BigDecimal.valueOf(12_000);
        StockTradeForm 매수거래 = StockTradeForm.builder()
                .type(TradeType.BUY)
                .tradeAt(tradeAt)
                .volume(volume)
                .price(price)
                .build();

        Long 매수ID = stockService.write(savedId, 매수거래);
        StockTrade 찾은거래 = stockTradeRepository.findById(매수ID);

        assertEquals("주식이 같아야 합니다.", 찾은거래.getStock().getId(), savedId);
        assertEquals("가격이 같아야 합니다.", 찾은거래.getPrice(), price);
        assertEquals("거래량이 같아야 합니다.", 찾은거래.getVolume(), volume);
        assertEquals("거래시각이 같아야 합니다.", 찾은거래.getTradeAt(), tradeAt);
        assertEquals("거래유형이 같아야 합니다.", 찾은거래.getType(), TradeType.BUY);
    }

    @Test
    @DisplayName("주식 정보가 정상적으로 저장됩니다")
    void save_ok() {
        Stock xx전자 = newStock("000001");

        Long savedId = stockService.register(StockForm.formFrom(xx전자));

        Stock findStock = stockRepository.findById(savedId);

        assertEquals("이름이 같습니다", xx전자.getName(), findStock.getName());
        assertEquals("코드가 같습니다", xx전자.getCode(), findStock.getCode());
        assertEquals("가격이 같습니다", xx전자.getPrice(), findStock.getPrice());
        assertEquals("발행주식수가 같습니다", xx전자.getIssuedAmount(), findStock.getIssuedAmount());
        assertEquals("시가총액이 같습니다", xx전자.getMarketCap(), findStock.getMarketCap());
        assertEquals("per이 같습니다", xx전자.getPer(), findStock.getPer());
        assertEquals("eps가 같습니다", xx전자.getEps(), findStock.getEps());
        assertEquals("상태가 같습니다", xx전자.getStatus(), findStock.getStatus());
    }

}