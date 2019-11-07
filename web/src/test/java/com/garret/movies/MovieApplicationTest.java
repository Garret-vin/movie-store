package com.garret.movies;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MovieApplication.class)
@Slf4j
@RunWith(SpringRunner.class)
public class MovieApplicationTest {

    @Test
    public void initContext() {
        log.info("Context initialized");
    }
}
