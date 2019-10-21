package com.garret.movies.config;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.service.api.MovieService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
@EnableBatchProcessing
public class SpringBatchConfig {

    private MovieService movieService;
    private OmdbClient omdbClient;
    private JobRepository jobRepository;

    @Bean
    public TaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(12);
        executor.setCorePoolSize(8);
        executor.setQueueCapacity(15);

        return executor;
    }

    @Bean
    public JobLauncher asyncJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(threadPoolTaskExecutor());
        return jobLauncher;
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Movie> itemReader,
                   ItemWriter<Movie> itemWriter) {

        Step step = stepBuilderFactory.get("OMBD-collection-load")
                .<Movie, Movie>chunk(10)
                .reader(itemReader)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("OMDB-load")
                .start(step)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Movie> itemReader(@Value("#{jobParameters[title]}") String title) {
        final List<Movie> newMovies = omdbClient.searchMovies(title)
                .stream()
                .filter(movie -> !movieService.existsInDb(movie))
                .collect(Collectors.toList());
        return new ListItemReader<>(newMovies);
    }



    @Bean
    @StepScope
    public ItemWriter writer() {
        return movieService::saveAll;
    }
}
