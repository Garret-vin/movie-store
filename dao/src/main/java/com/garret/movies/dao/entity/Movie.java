package com.garret.movies.dao.entity;

import com.garret.movies.dao.entity.enums.MovieType;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date released;
    private Double imdbRating;
    private Integer imdbVotes;
    private String title;
    private String runtime;
    private String director;
    private String plot;
    @Enumerated(EnumType.STRING)
    private MovieType type;
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
