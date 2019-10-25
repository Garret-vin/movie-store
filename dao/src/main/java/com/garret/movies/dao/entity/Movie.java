package com.garret.movies.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date released;
    private Double imdbRating;
    private Integer imdbVotes;
    private String title;
    private String runtime;
    private String director;
    @Column(length = 1024)
    private String plot;
    private String type;
    @Column(nullable = false)
    private String imdbId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Genre> genres;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Actor> actors;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Language> languages;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Country> countries;
}
