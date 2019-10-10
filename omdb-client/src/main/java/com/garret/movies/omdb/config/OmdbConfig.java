package com.garret.movies.omdb.config;

import com.omertron.omdbapi.OmdbApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OmdbConfig {

    @Value("${api_key}")
    private String API_KEY;

    @Bean
    public OmdbApi omdbApi() {
        return new OmdbApi(API_KEY);
    }
}
