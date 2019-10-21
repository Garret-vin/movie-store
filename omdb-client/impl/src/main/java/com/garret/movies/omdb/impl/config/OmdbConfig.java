package com.garret.movies.omdb.impl.config;

import com.omertron.omdbapi.OmdbApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/omdb.properties")
public class OmdbConfig {

    @Bean
    public OmdbApi omdbApi(@Value("${api_key}") String apiKey) {
        return new OmdbApi(apiKey);
    }
}
