package com.gangbean.josthis.domain;

import com.gangbean.josthis.exception.StockNotContainsInitialStateHistoryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class StockTest {

    @DisplayName("주식코드, 주식명, josthisScore는 변경이 있어도 반영되지 않고, 이력 또한 생성하지 않습니다.")
    @Test
    void merge_with_not_updatable() {
        // given
        Stock stock = new Stock.StockBuilder()
                .tickerCode("00000000000001")
                .name("XX전자")
                .price(new BigDecimal(10_000L))
                .per(new BigDecimal(1_500L))
                .eps(new BigDecimal(1_000L))
                .dividend(new BigDecimal(100L))
                .listedVolume(new BigDecimal(100_000_000L))
                .consensus(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY))
                .badStatus(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE)))
                .histories(new HashSet<>(Set.of(new StockHistory.StockHistoryBuilder()
                        .price(new BigDecimal(10_000L))
                        .per(new BigDecimal(1_500L))
                        .eps(new BigDecimal(1_000L))
                        .dividend(new BigDecimal(100L))
                        .listedVolume(new BigDecimal(100_000_000L))
                        .consensus(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY))
                        .badStatus(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE)))
                        .build())))
                .build();

        Stock updatableStock = new Stock.StockBuilder()
                .tickerCode("1")
                .name("test")
                .price(new BigDecimal(10_000L))
                .per(new BigDecimal(1_500L))
                .eps(new BigDecimal(1_000L))
                .dividend(new BigDecimal(100L))
                .listedVolume(new BigDecimal(100_000_000L))
                .consensus(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY))
                .badStatus(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE)))
                .josthisScore(new JosthisScore(new BigDecimal(98)))
                .histories(new HashSet<>(Set.of(new StockHistory.StockHistoryBuilder()
                        .price(new BigDecimal(10_000L))
                        .per(new BigDecimal(1_500L))
                        .eps(new BigDecimal(1_000L))
                        .dividend(new BigDecimal(100L))
                        .listedVolume(new BigDecimal(100_000_000L))
                        .consensus(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY))
                        .badStatus(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE)))
                        .josthisScore(new JosthisScore(new BigDecimal(98)))
                        .build())))
                .build();

        // when
        stock.update((id) -> updatableStock);

        // then
        assertAll(
                () -> assertThat(stock.getTickerCode()).isEqualTo("00000000000001"),
                () -> assertThat(stock.getName()).isEqualTo("XX전자"),
                () -> assertThat(stock.getPrice()).isEqualTo(new BigDecimal(10_000)),
                () -> assertThat(stock.getPer()).isEqualTo(new BigDecimal(1_500)),
                () -> assertThat(stock.getEps()).isEqualTo(new BigDecimal(1_000)),
                () -> assertThat(stock.getDividend()).isEqualTo(new BigDecimal(100)),
                () -> assertThat(stock.getListedVolume()).isEqualTo(new BigDecimal(100_000_000)),
                () -> assertThat(stock.getConsensus()).isEqualTo(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY)),
                () -> assertThat(stock.getBadStatus()).isEqualTo(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE))),
                () -> assertThat(stock.getHistories()).hasSize(1)
        );
    }

    @DisplayName("price, per, eps, 배당금, 상장주식수, 컨센서스, 비정상상태에 변경이 있으면 새로운 이력과 함께 반영 합니다.")
    @Test
    void mergeWithUpdatable() {
        // given
        Stock stock = new Stock.StockBuilder()
                .tickerCode("00000000000001")
                .name("XX전자")
                .price(new BigDecimal(10_000L))
                .per(new BigDecimal(1_500L))
                .eps(new BigDecimal(1_000L))
                .dividend(new BigDecimal(100L))
                .listedVolume(new BigDecimal(100_000_000L))
                .consensus(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY))
                .badStatus(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE)))
                .histories(new HashSet<>(Set.of(new StockHistory.StockHistoryBuilder()
                                        .price(new BigDecimal(10_000L))
                                        .per(new BigDecimal(1_500L))
                                        .eps(new BigDecimal(1_000L))
                                        .dividend(new BigDecimal(100L))
                                        .listedVolume(new BigDecimal(100_000_000L))
                                        .consensus(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY))
                                        .badStatus(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE)))
                                        .build())))
                .build();

        Stock updatableStock = new Stock.StockBuilder()
                .price(new BigDecimal(9_000))
                .per(new BigDecimal(1_400))
                .eps(new BigDecimal(900))
                .dividend(new BigDecimal(90))
                .listedVolume(new BigDecimal(120_000_000))
                .consensus(new Consensus(new BigDecimal("2.0"), ConsensusType.BUY))
                .badStatus(new HashSet<>(Set.of(BadStockStatus.ALERT)))
                .histories(new HashSet<>(Set.of(new StockHistory.StockHistoryBuilder()
                        .price(new BigDecimal(9_000))
                        .per(new BigDecimal(1_400))
                        .eps(new BigDecimal(900))
                        .dividend(new BigDecimal(90))
                        .listedVolume(new BigDecimal(120_000_000))
                        .consensus(new Consensus(new BigDecimal("2.0"), ConsensusType.BUY))
                        .badStatus(new HashSet<>(Set.of(BadStockStatus.ALERT)))
                        .build())))
                .build();

        // when
        stock.update((id) -> updatableStock);

        // then
        assertAll(
                () -> assertThat(stock.getTickerCode()).isEqualTo("00000000000001"),
                () -> assertThat(stock.getName()).isEqualTo("XX전자"),
                () -> assertThat(stock.getPrice()).isEqualTo(new BigDecimal(9_000)),
                () -> assertThat(stock.getPer()).isEqualTo(new BigDecimal(1_400)),
                () -> assertThat(stock.getEps()).isEqualTo(new BigDecimal(900)),
                () -> assertThat(stock.getDividend()).isEqualTo(new BigDecimal(90)),
                () -> assertThat(stock.getListedVolume()).isEqualTo(new BigDecimal(120_000_000)),
                () -> assertThat(stock.getConsensus()).isEqualTo(new Consensus(new BigDecimal("2.0"), ConsensusType.BUY)),
                () -> assertThat(stock.getBadStatus()).isEqualTo(new HashSet<>(Set.of(BadStockStatus.ALERT))),
                () -> assertThat(stock.getHistories()).hasSize(2)
        );
    }

    @DisplayName("주식은 초기상태와 다른 이력을 포함해 초기화시 오류를 반환합니다.")
    @Test
    void stock_must_contains_initial_state_history() {
        assertThatExceptionOfType(StockNotContainsInitialStateHistoryException.class)
                .isThrownBy(() -> new Stock.StockBuilder()
                        .tickerCode("00000000000001")
                        .name("XX전자")
                        .price(new BigDecimal(10_000L))
                        .per(new BigDecimal(1_500L))
                        .eps(new BigDecimal(1_000L))
                        .dividend(new BigDecimal(100L))
                        .listedVolume(new BigDecimal(100_000_000L))
                        .consensus(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY))
                        .badStatus(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE)))
                        .histories(Set.of(new StockHistory.StockHistoryBuilder()
                                        .price(new BigDecimal(9_000L))
                                        .per(new BigDecimal(1_500L))
                                        .eps(new BigDecimal(1_000L))
                                        .dividend(new BigDecimal(100L))
                                        .listedVolume(new BigDecimal(100_000_000L))
                                        .consensus(new Consensus(new BigDecimal("1.3"), ConsensusType.STRONG_BUY))
                                        .badStatus(new HashSet<>(Set.of(BadStockStatus.CLEARANCE_SALE)))
                                        .build()))
                        .build())
                .withMessage("주식은 최초상태의 이력을 가져야 합니다.");
    }

    @DisplayName("주식은 이력없는 초기화시 오류를 반환합니다.")
    @Test
    void stock_with_no_history() {
        assertThatExceptionOfType(StockNotContainsInitialStateHistoryException.class)
                .isThrownBy(() -> new Stock.StockBuilder()
                        .histories(null)
                        .build())
                .withMessage("주식은 최초상태의 이력을 가져야 합니다.");
    }

}