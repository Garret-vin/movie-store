package com.garret.movies.omdb.impl.client;

import com.garret.movies.dao.entity.MoviesDto;
import com.garret.movies.dao.entity.ShortMovie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class OmdbClientDTOImpl { //TODO impl

    private RestTemplate restTemplate;

    public OmdbClientDTOImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ShortMovie> searchMovies(String title) {
        int page = 1;
        boolean hasNextPage = true;
        List<ShortMovie> result = new ArrayList<>();
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("http://www.omdbapi.com/")
                .queryParam("apikey", "dfdcd469")
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
}
