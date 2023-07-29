package com.gangbean.josthis.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class BatchSchedule {

    private final Logger LOGGER = LoggerFactory.getLogger(BatchSchedule.class);

    private final ApplicationContext context;

    private final JobLauncher jobLauncher;

    public BatchSchedule(ApplicationContext context
            , JobLauncher jobLauncher) {
        this.context = context;
        this.jobLauncher = jobLauncher;
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void runStockUpdateBatch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        LOGGER.info(">>>>>>>>>>>>> START Scheduled mergeStocks Job <<<<<<<<<<<<<<<<<<");

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        Job stockUpdate = context.getBean("mergeStocks", Job.class);
        JobExecution execution = jobLauncher.run(stockUpdate, jobParameters);
        BatchStatus status = execution.getStatus();
        LOGGER.info(status.toString());
        LOGGER.info(">>>>>>>>>>>>> FINISHED Scheduled mergeStocks Job <<<<<<<<<<<<<<<<<<");
    }
}
