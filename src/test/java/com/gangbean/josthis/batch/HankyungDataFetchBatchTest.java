package com.gangbean.josthis.batch;

import com.gangbean.josthis.domain.*;
import com.gangbean.josthis.repository.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
@SpringBatchTest
@SpringBootTest
class HankyungDataFetchBatchTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private StockRepository stockRepository;

    @DisplayName("배치는 DB에 존재하는 주식의 정보를 업데이트합니다.")
    @Test
    void data_fetch_batch_update_stocks() throws Exception {
        // given
        stockRepository.deleteAll();
        stockRepository.saveAll(List.of(
                new Stock("XX전자"
                        , "KR7028050003"
                        , BigDecimal.ZERO
                        , Set.of(
                        new StockHistory(BigDecimal.ZERO
                                , BigDecimal.ZERO
                                , BigDecimal.ZERO
                                , BigDecimal.ZERO
                                , BigDecimal.ZERO
                                , Set.of()
                                , new Consensus(new BigDecimal("1.0"), ConsensusType.BUY)
                                , new JosthisScore(new BigDecimal("100"))))
                        , BigDecimal.ZERO
                        , BigDecimal.ZERO
                        , BigDecimal.ZERO
                        , BigDecimal.ZERO
                        , Set.of()
                        , new Consensus(new BigDecimal("1.0"), ConsensusType.BUY)
                        , new JosthisScore(new BigDecimal("100")))
        ));

        // when
        JobExecution execution = jobLauncherTestUtils.launchJob();

        // then
        assertThat(execution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }

}