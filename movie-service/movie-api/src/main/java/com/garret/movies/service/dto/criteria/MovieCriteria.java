package com.garret.movies.service.dto.criteria;

import lombok.Data;

@Data
public class MovieCriteria {

    private Integer year;
    private String genre;
    private String language;
    private String actor;
    private String country;
    private SortOptions order;
}
