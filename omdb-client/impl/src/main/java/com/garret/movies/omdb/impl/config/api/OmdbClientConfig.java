package com.garret.movies.omdb.impl.config.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/omdb.properties")
@ConfigurationProperties(prefix = "omdb")
@Data
public class OmdbClientConfig implements ApiClientConfig {
    private String url;
    private String apiKey;
}
