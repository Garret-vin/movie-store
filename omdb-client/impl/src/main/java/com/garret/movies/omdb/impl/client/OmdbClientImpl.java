package com.garret.movies.omdb.impl.client;

import com.garret.movies.dao.entity.Movie;
import com.garret.movies.omdb.api.OmdbClient;
import com.garret.movies.omdb.impl.util.MovieUtil;
import com.omertron.omdbapi.OMDBException;
import com.omertron.omdbapi.OmdbApi;
import com.omertron.omdbapi.model.OmdbVideoBasic;
import com.omertron.omdbapi.model.OmdbVideoFull;
import com.omertron.omdbapi.tools.OmdbParameters;
import com.omertron.omdbapi.tools.Param;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OmdbClientImpl implements OmdbClient {

    private OmdbApi omdbApi;

    @Autowired
    public OmdbClientImpl(OmdbApi omdbApi) {
        this.omdbApi = omdbApi;
    }

    @Override
    @Transactional
    public Movie searchMovieByTitle(@NonNull String title) {
        OmdbParameters params = new OmdbParameters();
        params.add(Param.TITLE, title);
        try {
            OmdbVideoFull omdbVideo = omdbApi.getInfo(params);
            return buildMovie(omdbVideo);
        } catch (OMDBException e) {
            log.error("Invalid parameters", e);
            throw new RuntimeException("Movie not found, please input valid parameters");
        }
    }

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

    /*private List<OmdbVideoBasic> getFullResponse(String title) throws OMDBException {
        List<OmdbVideoBasic> result = new ArrayList<>();
        SearchResults response = omdbApi.search(title);
        double totalPages = (double) response.getTotalResults() / 10;
        int page = 0;
        do {
            page++;
            OmdbParameters params = new OmdbParameters();
            params.add(Param.SEARCH, title);
            params.add(Param.fromString("page"), page);
            result.addAll(omdbApi.search(params).getResults());
        } while (totalPages >= page);
        return result;
    }*/

    private Movie getFromApiByImdbId(@NonNull String imdbId) {
        try {
            OmdbParameters parameters = new OmdbParameters();
            parameters.add(Param.IMDB, imdbId);
            OmdbVideoFull videoFromApi = omdbApi.getInfo(parameters);
            return buildMovie(videoFromApi);
        } catch (OMDBException e) {
            log.error("Movie building was failed", e);
            throw new RuntimeException("Something wrong in getFromApiById");
        }
    }

    private Movie buildMovie(OmdbVideoFull omdbVideo) {
        return Movie.builder()
                .actors(MovieUtil.actorsToList(omdbVideo.getActors()))
                .country(omdbVideo.getCountries().get(0))
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
