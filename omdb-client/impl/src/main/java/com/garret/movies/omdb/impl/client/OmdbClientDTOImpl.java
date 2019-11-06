package com.garret.movies.omdb.impl.client;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.MoviesResponse;
import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.impl.config.api.ApiClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class OmdbClientDTOImpl<T> implements OmdbClient<T> {

    private final ApiClientConfig clientConfig;
    private final RestTemplate restTemplate;

    @Override
    public MoviesResponse<T> searchMovies(String title, int page) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(clientConfig.getUrl())
                .queryParam("apikey", clientConfig.getApiKey())
                .queryParam("r", "json")
                .queryParam("s", title)
                .queryParam("page", page);
        return restTemplate.exchange(
                urlBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<MoviesResponse<T>>() {})
                .getBody();
    }

    @Override
    public OmdbMovie searchByImdbId(String imdbId) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(clientConfig.getUrl())
                .queryParam("apikey", clientConfig.getApiKey())
                .queryParam("i", imdbId);
        return restTemplate.getForObject(urlBuilder.toUriString(), OmdbMovie.class);
    }
}
