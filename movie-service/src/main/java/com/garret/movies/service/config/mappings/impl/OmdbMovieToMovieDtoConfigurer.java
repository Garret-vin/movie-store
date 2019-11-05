package com.garret.movies.service.config.mappings.impl;

import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.service.config.mappings.ModelMapperConfigurer;
import com.garret.movies.service.dto.ActorDto;
import com.garret.movies.service.dto.CountryDto;
import com.garret.movies.service.dto.GenreDto;
import com.garret.movies.service.dto.LanguageDto;
import com.garret.movies.service.dto.response.MovieDto;
import com.garret.movies.service.util.MovieUtil;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class OmdbMovieToMovieDtoConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        Converter<String, List<ActorDto>> toActorDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), ActorDto::new);
        Converter<String, List<GenreDto>> toGenresDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), GenreDto::new);
        Converter<String, List<LanguageDto>> toLanguageDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), LanguageDto::new);
        Converter<String, List<CountryDto>> toCountriesDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), CountryDto::new);
        Converter<String, Integer> toImdbVotesConverter = context ->
                MovieUtil.convertStringToInteger(context.getSource());
        Converter<String, Double> toImdbRatingDtoConverter = context ->
                MovieUtil.convertStringToDouble(context.getSource());
        Converter<String, Date> dateConverter = context ->
                MovieUtil.parseDate(context.getSource());

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
    }
}
