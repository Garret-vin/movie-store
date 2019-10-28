package com.garret.movies.config;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.entity.OmdbMovie;
import com.garret.movies.service.dto.*;
import com.garret.movies.util.MovieUtil;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.List;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

        Converter<String, List<ActorDto>> toActorDtoConverter = context -> MovieUtil.actorsToList(context.getSource());
        Converter<String, List<GenreDto>> toGenresDtoConverter = context -> MovieUtil.genresToList(context.getSource());
        Converter<String, List<LanguageDto>> toLanguageDtoConverter = context -> MovieUtil.languagesToList(context.getSource());
        Converter<String, List<CountryDto>> toCountriesDtoConverter = context -> MovieUtil.countiesToList(context.getSource());
        Converter<String, Integer> toImdbVotesConverter = context -> MovieUtil.convertStringToInteger(context.getSource());
        Converter<String, Double> toImdbRatingDtoConverter = context -> MovieUtil.convertStringToDouble(context.getSource());
        Converter<String, Date> dateConverter = context -> MovieUtil.parseDate(context.getSource());

        modelMapper.addConverter(toActorDtoConverter);
        modelMapper.addConverter(toGenresDtoConverter);
        modelMapper.addConverter(toLanguageDtoConverter);
        modelMapper.addConverter(toCountriesDtoConverter);
        modelMapper.addConverter(toImdbVotesConverter);
        modelMapper.addConverter(toImdbRatingDtoConverter);
        modelMapper.addConverter(dateConverter);

        PropertyMap<OmdbMovie, MovieDto> omdbToDtoMapping = new PropertyMap<OmdbMovie, MovieDto>() {
            @Override
            protected void configure() {
                using(toActorDtoConverter).map(source.getActors()).setActors(null);
                using(toGenresDtoConverter).map(source.getGenres()).setGenres(null);
                using(toLanguageDtoConverter).map(source.getLanguages()).setLanguages(null);
                using(toCountriesDtoConverter).map(source.getCountries()).setCountries(null);
                using(toImdbVotesConverter).map(source.getImdbVotes()).setImdbVotes(null);
                using(toImdbRatingDtoConverter).map(source.getImdbRating()).setImdbRating(null);
                using(dateConverter).map(source.getReleased()).setReleased(null);
            }
        };
        modelMapper.addMappings(omdbToDtoMapping);

        PropertyMap<MovieDto, Movie> dtoToEntityMapping = new PropertyMap<MovieDto, Movie>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };
        modelMapper.addMappings(dtoToEntityMapping);
        return modelMapper;
    }

}
