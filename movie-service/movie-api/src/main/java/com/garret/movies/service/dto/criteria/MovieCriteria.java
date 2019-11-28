package com.garret.movies.service.dto.criteria;

import lombok.Data;

@Data
public class MovieCriteria {

    private int year;
    private String genre;
    private String language;
    private String actor;
    private String country;
    private String order;
}
