package com.garret.movies.controller;

import com.garret.movies.service.api.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieDatabaseController.class)
public class MovieDatabaseControllerTest {

    @Autowired
    private MovieDatabaseController controller;

    @MockBean
    private MovieService movieService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getByGenre() {
    }

    @Test
    public void getByLanguage() {
    }

    @Test
    public void getByCountry() {
    }

    @Test
    public void getByActor() {
    }

    @Test
    public void getByYear() {
    }

    @Test
    public void getTopByVotes() {
    }

    @Test
    public void getTopByRating() {
    }

    @Test
    public void getAllMovies() {
    }

    @Test
    public void deleteById() {
    }
}
