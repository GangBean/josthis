package com.gangbean.josthis.batch;

import com.gangbean.josthis.domain.Stock;
import com.gangbean.josthis.domain.StockFetchSource;
import com.gangbean.josthis.exception.StockException;
import com.gangbean.josthis.repository.StockRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@EnableBatchProcessing
@Configuration
public class HankyungDataFetchBatch {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;

    private final StockRepository stockRepository;

    private final ApplicationContext applicationContext;

    public HankyungDataFetchBatch(JobBuilderFactory jobBuilderFactory
            , StepBuilderFactory stepBuilderFactory
            , EntityManagerFactory entityManagerFactory
            , StockRepository stockRepository
            , ApplicationContext applicationContext) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.stockRepository = stockRepository;
        this.applicationContext = applicationContext;
    }

    @Bean
    public Job mergeStocks(Step step) {
        return jobBuilderFactory.get("mergeStocks")
                .start(step)
                .build();
    }

    @Bean
    public Step fetchStocks(ItemReader<Stock> reader, ItemProcessor<Stock, Stock> processor, ItemWriter<Stock> writer) {
        return stepBuilderFactory.get("fetchStocks")
                .<Stock, Stock>chunk(10)
                .reader(reader)
                .faultTolerant()
                .skip(StockException.class)
                .skipLimit(1000)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public JpaCursorItemReader<Stock> readAllStocks() {
        return new JpaCursorItemReaderBuilder<Stock>()
                .name("readAllStocks")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT s FROM Stock s")
                .build();
    }

    @Bean
    public ItemProcessor<Stock, Stock> executeTrade() {
        return stock -> {
            stock.update(applicationContext.getBean(StockFetchSource.class));
            return stock;
        };
    }

    @Bean
    public ItemWriter<Stock> reservationWriter() {
        return stockRepository::saveAll;
    }
}
