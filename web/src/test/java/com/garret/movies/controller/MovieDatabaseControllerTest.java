package com.garret.movies.controller;

import com.garret.movies.MovieApplication;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.*;
import com.garret.movies.service.dto.response.MovieDto;
import com.garret.movies.service.dto.response.SimpleMoviesResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
                MovieDatabaseController.class,
                MovieApplication.class
        })
@AutoConfigureMockMvc
public class MovieDatabaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;
    @Autowired
    private MovieDatabaseController controller;

    private MovieDto movieDto;

    @Before
    public void setUp() {
        movieDto = new MovieDto();
        movieDto.setImdbId("tt223344");
        CountryDto countryDto = new CountryDto();
        countryDto.setValue("USA");
        movieDto.setCountries(Collections.singletonList(countryDto));
        GenreDto genreDto = new GenreDto();
        genreDto.setValue("ACTION");
        movieDto.setGenres(Collections.singletonList(genreDto));
        ActorDto actorDto = new ActorDto();
        actorDto.setValue("Nikole Kidman");
        movieDto.setActors(Collections.singletonList(actorDto));
        LanguageDto languageDto = new LanguageDto();
        languageDto.setValue("English");
        movieDto.setLanguages(Collections.singletonList(languageDto));
    }

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getById() throws Exception {
        when(movieService.getById(2L)).thenReturn(Optional.of(movieDto));
        mockMvc.perform(get("/movies/2"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").hasJsonPath())
                .andExpect(jsonPath("$.released").hasJsonPath())
                .andExpect(jsonPath("$.imdbRating").hasJsonPath())
                .andExpect(jsonPath("$.imdbVotes").hasJsonPath())
                .andExpect(jsonPath("$.runtime").hasJsonPath())
                .andExpect(jsonPath("$.director").hasJsonPath())
                .andExpect(jsonPath("$.plot").hasJsonPath())
                .andExpect(jsonPath("$.type").hasJsonPath())
                .andExpect(jsonPath("$.imdbId").exists())
                .andExpect(jsonPath("$.genres").exists())
                .andExpect(jsonPath("$.actors").exists())
                .andExpect(jsonPath("$.languages").exists())
                .andExpect(jsonPath("$.countries").exists());
        verify(movieService).getById(2L);
    }

    @Test
    public void findAll() throws Exception {
        List<SimpleMovieDto> simpleMovieList = Collections.singletonList(new SimpleMovieDto());
        SimpleMoviesResponse response = new SimpleMoviesResponse();
        response.setSearchContent(simpleMovieList);
        response.setPageSize(10);
        response.setTotalElements(20L);
        when(movieService.getAll(any(Pageable.class))).thenReturn(response);

        mockMvc.perform(get("/movies"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.searchContent").exists())
                .andExpect(jsonPath("$.pageSize").value(10))
                .andExpect(jsonPath("$.totalElements").value(20));
        verify(movieService).getAll(any(Pageable.class));
    }

    @Test
    public void deleteById() throws Exception {
        doNothing().when(movieService).deleteById(2L);
        mockMvc.perform(delete("/movies/2"))
                .andExpect(status().isOk());
        verify(movieService).deleteById(2L);
    }

    @Test
    public void deleteAll() throws Exception {
        doNothing().when(movieService).deleteAll();
        mockMvc.perform(delete("/movies"))
                .andExpect(status().isOk());
        verify(movieService).deleteAll();
    }
}