package com.garret.movies.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date released;
    private String runtime;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Genre> genres;
    private String director;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Actor> actors;
    @Column(length = 1024)
    private String plot;
    private String type;
    @Column(nullable = false)
    private String imdbId;
    private Double imdbRating;
    private Integer imdbVotes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Language> languages;
    @ElementCollection
    @CollectionTable(name = "country", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "country")
    private List<String> country;
}
