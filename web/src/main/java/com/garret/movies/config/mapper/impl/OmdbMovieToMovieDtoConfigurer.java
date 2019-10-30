package com.garret.movies.config.mapper.impl;

import com.garret.movies.config.mapper.ModelMapperConfigurer;
import com.garret.movies.omdb.entity.OmdbMovie;
import com.garret.movies.service.dto.*;
import com.garret.movies.service.dto.response.MovieDto;
import com.garret.movies.util.MovieUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class OmdbMovieToMovieDtoConfigurer implements ModelMapperConfigurer {

    private final ModelMapper modelMapper;

    @Override
    public void configure() {
        Converter<String, List<ActorDto>> toActorDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), ActorDto.class);
        Converter<String, List<GenreDto>> toGenresDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), GenreDto.class);
        Converter<String, List<LanguageDto>> toLanguageDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), LanguageDto.class);
        Converter<String, List<CountryDto>> toCountriesDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), CountryDto.class);
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