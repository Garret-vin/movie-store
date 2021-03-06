package com.garret.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class);
    }
}
