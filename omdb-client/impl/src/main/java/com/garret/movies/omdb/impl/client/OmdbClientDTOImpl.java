package com.garret.movies.omdb.impl.client;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.MoviesResponse;
import com.garret.movies.omdb.dto.OmdbMovie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OmdbClientDTOImpl implements OmdbClient {

    private final String url;
    private final String apiKey;
    private final RestTemplate restTemplate;

    public OmdbClientDTOImpl(@Value("${api_url}") String url,
                             @Value("${api_key}") String apiKey,
                             RestTemplate restTemplate) {
        this.url = url;
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    @Override
    public MoviesResponse searchMovies(String title, int page) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apikey", apiKey)
                .queryParam("r", "json")
                .queryParam("s", title)
                .queryParam("page", page);
        return restTemplate.getForObject(urlBuilder.toUriString(), MoviesResponse.class);
    }

    @Override
    public OmdbMovie searchByImdbId(String imdbId) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apikey", apiKey)
                .queryParam("i", imdbId);

        return restTemplate.getForObject(urlBuilder.toUriString(), OmdbMovie.class);
    }
}
