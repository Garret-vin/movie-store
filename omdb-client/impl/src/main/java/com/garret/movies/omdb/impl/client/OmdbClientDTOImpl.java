package com.garret.movies.omdb.impl.client;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.entity.MoviesDto;
import com.garret.movies.dao.entity.ShortMovie;
import com.garret.movies.omdb.api.OmdbClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OmdbClientDTOImpl implements OmdbClient {

    @Value("${api_url}")
    private String url;
    @Value("${api_key}")
    private String apiKey;
    private final RestTemplate restTemplate;

    @Override
    public List<ShortMovie> searchMovies(String title) {
        int page = 1;
        boolean hasNextPage = true;
        List<ShortMovie> result = new ArrayList<>();
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apikey", apiKey)
                .queryParam("r", "json")
                .queryParam("s", title)
                .queryParam("page", page);
        while (hasNextPage) {
            MoviesDto response = restTemplate.getForObject(urlBuilder.toUriString(), MoviesDto.class);
            if (response != null && response.getResponse().equals("True")) {
                result.addAll(response.getShortMovieList());
                urlBuilder.replaceQueryParam("page", ++page);
            } else {
                hasNextPage = false;
            }
        }
        return result;
    }

    @Override
    public Movie searchByImdbId(String imdbId) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apikey", apiKey)
                .queryParam("i", imdbId);

        return restTemplate.getForObject(urlBuilder.toUriString(), Movie.class);
    }
}
