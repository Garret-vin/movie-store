package com.garret.movies.common.config.mapper.impl;

import com.garret.movies.common.config.mapper.ModelMapperConfigurer;
import com.garret.movies.common.util.MovieUtil;
import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.service.dto.ActorDto;
import com.garret.movies.service.dto.CountryDto;
import com.garret.movies.service.dto.GenreDto;
import com.garret.movies.service.dto.LanguageDto;
import com.garret.movies.service.dto.enums.MovieTypeDto;
import com.garret.movies.service.dto.response.MovieDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class OmdbMovieToMovieDtoConfigurer implements ModelMapperConfigurer {

    @Override
    public void configure(ModelMapper modelMapper) {
        Converter<String, MovieTypeDto> movieTypeConverter = context ->
                MovieTypeDto.valueOf(context.getSource().toUpperCase());

        Converter<String, List<ActorDto>> toActorDtoConverter = context ->
                MovieUtil.stringToValuableList(context.getSource(), ActorDto::new);

        Converter<String, List<GenreDto>> toGenresDtoConverter = context ->
                MovieUtil.stringToValuableList(convertToGenreString(context.getSource()), GenreDto::new);

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

        modelMapper.createTypeMap(OmdbMovie.class, MovieDto.class)
                .addMappings(mapper -> {
                    mapper.using(movieTypeConverter).map(OmdbMovie::getType, MovieDto::setType);
                    mapper.using(toActorDtoConverter).map(OmdbMovie::getActors, MovieDto::setActors);
                    mapper.using(toGenresDtoConverter).map(OmdbMovie::getGenres, MovieDto::setGenres);
                    mapper.using(toLanguageDtoConverter).map(OmdbMovie::getLanguages, MovieDto::setLanguages);
                    mapper.using(toCountriesDtoConverter).map(OmdbMovie::getCountries, MovieDto::setCountries);
                    mapper.using(toImdbVotesConverter).map(OmdbMovie::getImdbVotes, MovieDto::setImdbVotes);
                    mapper.using(toImdbRatingDtoConverter).map(OmdbMovie::getImdbRating, MovieDto::setImdbRating);
                    mapper.using(dateConverter).map(OmdbMovie::getReleased, MovieDto::setReleased);
                });
    }

    private String convertToGenreString(String source) {
        return source.replaceAll("-", "_").toUpperCase();
    }
}
