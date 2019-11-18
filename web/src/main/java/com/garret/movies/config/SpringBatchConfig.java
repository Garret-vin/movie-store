package com.garret.movies.config;

import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.dto.SimpleMovie;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@AllArgsConstructor
@EnableBatchProcessing
public class SpringBatchConfig {

    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(12);
        executor.setCorePoolSize(8);
        executor.setQueueCapacity(15);

        return executor;
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory.get("OMDB-load")
                .start(step)
                .build();
    }

    @Bean
    public Step step(ItemReader<SimpleMovie> itemReader,
                     ItemProcessor<SimpleMovie, OmdbMovie> itemProcessor,
                     ItemWriter<OmdbMovie> itemWriter) {
        return stepBuilderFactory.get("OMBD-collection-load")
                .<SimpleMovie, OmdbMovie>chunk(50)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .taskExecutor(threadPoolTaskExecutor())
                .build();
    }
}
