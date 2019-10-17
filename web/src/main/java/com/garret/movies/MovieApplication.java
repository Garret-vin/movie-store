package com.garret.movies;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableBatchProcessing
@PropertySource(value = {"application.properties", "omdb.properties", "dao.properties"})
public class MovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class);
    }
}
