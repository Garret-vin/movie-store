package com.garret.movies.omdb.impl.client;

import com.garret.movies.dao.entity.Movie;
import com.omertron.omdbapi.OMDBException;
import com.omertron.omdbapi.OmdbApi;
import com.omertron.omdbapi.model.OmdbVideoBasic;
import com.omertron.omdbapi.model.OmdbVideoFull;
import com.omertron.omdbapi.model.SearchResults;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OmdbClientImplTest {

    private OmdbApi omdbApi = mock(OmdbApi.class);
    private OmdbClientImpl omdbClient = new OmdbClientImpl(omdbApi);

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

        verify(omdbApi).search(title);
        verify(omdbApi, times(result.size())).getInfo(any());
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
