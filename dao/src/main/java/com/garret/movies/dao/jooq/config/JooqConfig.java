package com.garret.movies.dao.jooq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/dao.properties")
public class JooqConfig {
}
