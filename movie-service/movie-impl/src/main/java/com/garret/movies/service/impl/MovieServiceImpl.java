package com.garret.movies.service.impl;

import com.garret.movies.common.exception.IncorrectDateException;
import com.garret.movies.dao.entity.Actor;
import com.garret.movies.dao.entity.Country;
import com.garret.movies.dao.entity.Genre;
import com.garret.movies.dao.entity.Language;
import com.garret.movies.dao.entity.Movie;
import com.garret.movies.dao.repository.MovieRepository;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.criteria.MovieCriteria;
import com.garret.movies.service.dto.criteria.SortOptions;
import com.garret.movies.service.dto.response.MovieDto;
import com.garret.movies.service.dto.response.SimpleMoviesResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.jooq.SelectFinalStep;
import org.jooq.SelectQuery;
import org.jooq.TableField;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.garret.movies.dao.jooq.generated.Tables.*;

@Service
@Slf4j
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private static final Type MOVIE_LIST_TYPE = new TypeToken<List<Movie>>() {
    }.getType();
    private static final Type MOVIE_DTO_LIST_TYPE = new TypeToken<List<MovieDto>>() {
    }.getType();
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private MovieRepository movieRepository;
    private ModelMapper modelMapper;
    private DSLContext jooq;

    @Override
    @Transactional
    public MovieDto save(@NonNull MovieDto movieDto) {
        return movieRepository.findByImdbId(movieDto.getImdbId()).map(this::convertToDto).orElseGet(() -> {
            log.info("Saving movie " + movieDto.getTitle());
            Movie movie = modelMapper.map(movieDto, Movie.class);
            Movie savedMovie = movieRepository.save(movie);
            return convertToDto(savedMovie);
        });
    }

    @Override
    @Transactional
    public List<MovieDto> saveAll(@NonNull List<MovieDto> movieDtoList) {
        List<Movie> movieList = modelMapper.map(movieDtoList, MOVIE_LIST_TYPE);
        log.info("Saving " + movieList.size() + " movies to database");
        List<Movie> savedMovies = movieRepository.saveAll(movieList);
        return modelMapper.map(savedMovies, MOVIE_DTO_LIST_TYPE);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieDto> getById(@NonNull Long id) {
        return movieRepository.findById(id).map(this::convertToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleMoviesResponse getAll(Pageable pageable) {
        Page<Movie> fullPage = movieRepository.findAll(pageable);
        return modelMapper.map(fullPage, SimpleMoviesResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getByRequestParams(MovieCriteria movieCriteria) {
        SelectConditionStep<Record> query = jooq.select()
                .from(MOVIE)
                .leftJoin(MOVIE_ACTORS).on(MOVIE.ID.eq(MOVIE_ACTORS.MOVIE_ID))
                .leftJoin(ACTOR).on(MOVIE_ACTORS.ACTORS_ID.eq(ACTOR.ID))
                .leftJoin(MOVIE_COUNTRIES).on(MOVIE.ID.eq(MOVIE_COUNTRIES.MOVIE_ID))
                .leftJoin(COUNTRY).on(MOVIE_COUNTRIES.COUNTRIES_ID.eq(COUNTRY.ID))
                .leftJoin(MOVIE_GENRES).on(MOVIE.ID.eq(MOVIE_GENRES.MOVIE_ID))
                .leftJoin(GENRE).on(MOVIE_GENRES.GENRES_ID.eq(GENRE.ID))
                .leftJoin(MOVIE_LANGUAGES).on(MOVIE.ID.eq(MOVIE_LANGUAGES.MOVIE_ID))
                .leftJoin(LANGUAGE).on(MOVIE_LANGUAGES.LANGUAGES_ID.eq(LANGUAGE.ID))
                .where();
        getMovieIdSetByParams(movieCriteria).forEach(movieId -> query.or(MOVIE.ID.eq(movieId)));
        addMovieOrdering(query, movieCriteria.getOrder());

        Map<Record, Result<Record>> recordResultMap = query.fetch().intoGroups(MOVIE.fields());
        List<Movie> movieList = buildRecordsMapToList(recordResultMap);
        return modelMapper.map(movieList, MOVIE_DTO_LIST_TYPE);
    }

    private void addMovieOrdering(SelectConditionStep<Record> query, SortOptions orderingBy) {
        Optional.ofNullable(orderingBy)
                .ifPresent(order -> {
                    if (order.equals(SortOptions.VOTES)) {
                        query.orderBy(MOVIE.IMDB_VOTES.desc());
                    } else if (order.equals(SortOptions.RATING)) {
                        query.orderBy(MOVIE.IMDB_RATING.desc());
                    }
                });
    }

    private Set<Long> getMovieIdSetByParams(MovieCriteria movieCriteria) {
        SelectFinalStep<?> finalStep = jooq.select(MOVIE.ID)
                .from(MOVIE)
                .leftJoin(MOVIE_ACTORS).on(MOVIE.ID.eq(MOVIE_ACTORS.MOVIE_ID))
                .leftJoin(ACTOR).on(MOVIE_ACTORS.ACTORS_ID.eq(ACTOR.ID))
                .leftJoin(MOVIE_COUNTRIES).on(MOVIE.ID.eq(MOVIE_COUNTRIES.MOVIE_ID))
                .leftJoin(COUNTRY).on(MOVIE_COUNTRIES.COUNTRIES_ID.eq(COUNTRY.ID))
                .leftJoin(MOVIE_GENRES).on(MOVIE.ID.eq(MOVIE_GENRES.MOVIE_ID))
                .leftJoin(GENRE).on(MOVIE_GENRES.GENRES_ID.eq(GENRE.ID))
                .leftJoin(MOVIE_LANGUAGES).on(MOVIE.ID.eq(MOVIE_LANGUAGES.MOVIE_ID))
                .leftJoin(LANGUAGE).on(MOVIE_LANGUAGES.LANGUAGES_ID.eq(LANGUAGE.ID));
        SelectQuery<?> query = finalStep.getQuery();

        addCondition(query, GENRE.NAME, movieCriteria::getGenre);
        addCondition(query, LANGUAGE.NAME, movieCriteria::getLanguage);
        addCondition(query, ACTOR.FULL_NAME, movieCriteria::getActor);
        addCondition(query, COUNTRY.NAME, movieCriteria::getCountry);
        Optional.ofNullable(movieCriteria.getYear())
                .ifPresent(year ->
                        query.addConditions(MOVIE.RELEASED.between(toDate(startOf(year)), toDate(endOf(year)))));
        return query.fetch().intoSet(MOVIE.ID);
    }

    private String startOf(int year) {
        return year + "-01-01";
    }

    private String endOf(int year) {
        return year + "-12-31";
    }

    private <T> void addCondition(SelectQuery<?> query, TableField<?, T> field, Supplier<T> conditionValue) {
        Optional.ofNullable(conditionValue.get())
                .ifPresent(value -> query.addConditions(field.contains(value)));
    }

    private Date toDate(String date) {
        try {
            return new Date(DATE_FORMATTER.parse(date).getTime());
        } catch (ParseException e) {
            log.error("Wrong input date format. Expected yyyy-MM-dd but received " + date, e);
            throw new IncorrectDateException("Can't parse date from DB");
        }
    }

    private List<Movie> buildRecordsMapToList(@NonNull Map<Record, Result<Record>> recordResultMap) {
        return recordResultMap.values()
                .stream()
                .map(res -> {
                    Movie movie = res.into(MOVIE).into(Movie.class).get(0);
                    List<Actor> actors = res.into(ACTOR)
                            .into(Actor.class)
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());
                    movie.setActors(actors);
                    List<Genre> genres = res.into(GENRE)
                            .into(Genre.class)
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());
                    movie.setGenres(genres);
                    List<Country> countries = res.into(COUNTRY)
                            .into(Country.class)
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());
                    movie.setCountries(countries);
                    List<Language> languages = res.into(LANGUAGE)
                            .into(Language.class)
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());
                    movie.setLanguages(languages);
                    return movie;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieDto> getByImdbId(@NonNull String imdbId) {
        return movieRepository.findByImdbId(imdbId).map(this::convertToDto);
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        movieRepository.deleteById(id);
        log.info("Deleting movie with id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByImdbId(@NonNull String imdbId) {
        return movieRepository.existsByImdbId(imdbId);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Deleting all movies from database");
        movieRepository.deleteAll();
    }

    private MovieDto convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }
}
