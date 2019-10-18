package com.garret.movies.omdb.impl.client;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.impl.exception.OmdbApiException;
import com.garret.movies.omdb.impl.util.MovieUtil;
import com.omertron.omdbapi.OMDBException;
import com.omertron.omdbapi.OmdbApi;
import com.omertron.omdbapi.model.OmdbVideoBasic;
import com.omertron.omdbapi.model.OmdbVideoFull;
import com.omertron.omdbapi.tools.OmdbParameters;
import com.omertron.omdbapi.tools.Param;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OmdbClientImpl implements OmdbClient {

    private OmdbApi omdbApi;

    @Override
    @Transactional
    public List<Movie> searchMovies(@NonNull String title) {
        try {
            List<OmdbVideoBasic> videoList = ListUtils.emptyIfNull(omdbApi.search(title).getResults());
            return ListUtils.emptyIfNull(videoList.stream()
                    .map(OmdbVideoBasic::getImdbID)
                    .map(this::getFromApiByImdbId)
                    .collect(Collectors.toList()));
        } catch (OMDBException e) {
            log.error("Invalid parameters", e);
        }
        return Collections.emptyList();
    }

    private Movie getFromApiByImdbId(@NonNull String imdbId) {
        try {
            OmdbParameters parameters = new OmdbParameters();
            parameters.add(Param.IMDB, imdbId);
            OmdbVideoFull videoFromApi = omdbApi.getInfo(parameters);
            return buildMovie(videoFromApi);
        } catch (OMDBException e) {
            log.error("Movie building was failed", e);
            throw new OmdbApiException("Something wrong in getFromApiById");
        }
    }

    private Movie buildMovie(OmdbVideoFull omdbVideo) {
        return Movie.builder()
                .actors(MovieUtil.actorsToList(omdbVideo.getActors()))
                .country(omdbVideo.getCountries().stream().map(String::trim).collect(Collectors.toList()))
                .director(omdbVideo.getDirector())
                .genres(MovieUtil.genresToList(omdbVideo.getGenre()))
                .imdbId(omdbVideo.getImdbID())
                .imdbRating(Double.valueOf(omdbVideo.getImdbRating()))
                .imdbVotes(MovieUtil.convertVotesToInt(omdbVideo.getImdbVotes()))
                .languages(MovieUtil.languagesToList(omdbVideo.getLanguages()))
                .plot(omdbVideo.getPlot())
                .released(MovieUtil.parseDate(omdbVideo.getReleased()))
                .runtime(omdbVideo.getRuntime())
                .title(omdbVideo.getTitle())
                .type(omdbVideo.getType())
                .build();
    }
}
