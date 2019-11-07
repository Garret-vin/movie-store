package com.garret.movies.omdb.impl.client;

import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.dto.OmdbApiResponse;
import com.garret.movies.omdb.dto.OmdbApiSearchResponse;
import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.dto.SimpleMovie;
import com.garret.movies.omdb.impl.config.api.ApiClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OmdbClientDTOImpl implements OmdbClient {

    private final ApiClientConfig clientConfig;
    private final RestTemplate restTemplate;

    @Override
    public OmdbApiSearchResponse<List<SimpleMovie>> searchMovies(String title, int page) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(clientConfig.getUrl())
                .queryParam("apikey", clientConfig.getApiKey())
                .queryParam("r", "json")
                .queryParam("s", title)
                .queryParam("page", page);
        return sendGetRequestToApi(urlBuilder,
                new ParameterizedTypeReference<OmdbApiSearchResponse<List<SimpleMovie>>>() {
                });
    }

    @Override
    public OmdbApiResponse<OmdbMovie> searchByImdbId(String imdbId) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(clientConfig.getUrl())
                .queryParam("apikey", clientConfig.getApiKey())
                .queryParam("i", imdbId);
        return sendGetRequestToApi(urlBuilder, new ParameterizedTypeReference<OmdbApiResponse<OmdbMovie>>() {
        });
    }

    private <T> T sendGetRequestToApi(UriComponentsBuilder urlBuilder, ParameterizedTypeReference<T> typeReference) {
        return restTemplate.exchange(
                urlBuilder.toUriString(),
                HttpMethod.GET,
                null,
                typeReference)
                .getBody();
    }
}
