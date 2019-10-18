package com.garret.movies.config;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.service.api.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
public class SpringBatchConfig {

    private MovieService movieService;
    private OmdbClient omdbClient;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Movie> itemReader,
                   ItemProcessor<Movie, Movie> itemProcessor,
                   ItemWriter<Movie> itemWriter) {

        Step step = stepBuilderFactory.get("OMBD-collection-load")
                .<Movie, Movie>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
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
    public ItemProcessor<Movie, Movie> processor() {
        return movie -> movie;
    }

    @Bean
    public ItemWriter writer() {
        return movieService::saveAll;
    }
}
