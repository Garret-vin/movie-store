package com.garret.movies.omdb.impl.client;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.api.OmdbClient;
import com.omertron.omdbapi.OMDBException;
import com.omertron.omdbapi.OmdbApi;
import com.omertron.omdbapi.model.OmdbVideoBasic;
import com.omertron.omdbapi.model.OmdbVideoFull;
import com.omertron.omdbapi.model.SearchResults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OmdbClientImpl.class)
public class OmdbClientImplTest {

    @MockBean
    private OmdbApi omdbApi;

    @Autowired
    private OmdbClient omdbClient;

    @Test
    public void testSearchMovies() throws OMDBException {
        String title = "batman";
        SearchResults searchResults = new SearchResults();
        OmdbVideoBasic omdbVideoBasic = new OmdbVideoBasic();
        omdbVideoBasic.setImdbID("tt223344");
        OmdbVideoBasic omdbVideoBasic2 = new OmdbVideoBasic();
        omdbVideoBasic.setImdbID("dd334455");
        searchResults.setResults(Stream.of(omdbVideoBasic, omdbVideoBasic2).collect(Collectors.toList()));
        OmdbVideoFull fullVideo = setUpVideoFull();

        when(omdbApi.search(title)).thenReturn(searchResults);
        when(omdbApi.getInfo(any())).thenReturn(fullVideo);

        List<Movie> result = omdbClient.searchMovies(title);

        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result.get(0).getImdbId()).isNotNull();

        verify(omdbApi, times(1)).search(title);
        verify(omdbApi, times(result.size())).getInfo(any());
    }

    @Test(expected = NullPointerException.class)
    public void testSearchMovies_Null_Param() {
        omdbClient.searchMovies(null);
    }
    private OmdbVideoFull setUpVideoFull() {
        OmdbVideoFull fullVideo = new OmdbVideoFull();
        fullVideo.setActors("Arni");
        fullVideo.setCountryList("USA");
        fullVideo.setDirector("Tarantino");
        fullVideo.setGenre("Action");
        fullVideo.setImdbID("imdb");
        fullVideo.setImdbRating("7.8");
        fullVideo.setImdbVotes("123,456");
        fullVideo.setLanguageList("English");
        fullVideo.setPlot("Good plot");
        fullVideo.setReleased("25 Jun 2014");
        fullVideo.setRuntime("140 min");
        fullVideo.setTitle("Title of");
        fullVideo.setType("movie");
        return fullVideo;
    }
}
