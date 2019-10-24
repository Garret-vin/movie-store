package com.garret.movies.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.garret.movies.dao.util.MovieUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "released")
    private Date released;
    @Column(name = "imdb_rating")
    private Double imdbRating;
    @Column(name = "imdb_votes")
    private Integer imdbVotes;
    @JsonProperty("Title")
    @Column(name = "title")
    private String title;
    @JsonProperty("Runtime")
    @Column(name = "runtime")
    private String runtime;
    @JsonProperty("Director")
    @Column(name = "director")
    private String director;
    @JsonProperty("Plot")
    @Column(name = "plot", length = 1024)
    private String plot;
    @JsonProperty("Type")
    @Column(name = "type")
    private String type;
    @JsonProperty("imdbID")
    @Column(name = "imdb_id", nullable = false)
    private String imdbId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Genre> genres;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Actor> actors;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Language> languages;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Country> countries;

    public Movie(@JsonProperty("Released") String released,
                 @JsonProperty("imdbVotes") String imdbVotes,
                 @JsonProperty("Genre") String genres,
                 @JsonProperty("Actors") String actors,
                 @JsonProperty("Language") String languages,
                 @JsonProperty("Country") String counties,
                 @JsonProperty("imdbRating") String imdbRating) {
        this.released = MovieUtil.parseDate(released);
        this.imdbVotes = MovieUtil.convertStringToInteger(imdbVotes);
        this.genres = MovieUtil.genresToList(genres);
        this.actors = MovieUtil.actorsToList(actors);
        this.languages = MovieUtil.languagesToList(languages);
        this.countries = MovieUtil.countiesToList(counties);
        this.imdbRating = MovieUtil.convertStringToDouble(imdbRating);
    }
}
