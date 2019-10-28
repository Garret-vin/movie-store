package com.garret.movies.service.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class MovieDto {

    private String title;
    private Date released;
    private Double imdbRating;
    private Integer imdbVotes;
    private String runtime;
    private String director;
    private String plot;
    private String type;
    private String imdbId;
    private List<GenreDto> genres;
    private List<ActorDto> actors;
    private List<LanguageDto> languages;
    private List<CountryDto> countries;
}
