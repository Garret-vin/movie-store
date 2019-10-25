package com.garret.movies.controller;

import com.garret.movies.omdb.dto.OmdbMovie;
import com.garret.movies.omdb.impl.client.OmdbClientDTOImpl;
import com.garret.movies.service.api.MovieService;
import com.garret.movies.service.dto.MovieDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
@AllArgsConstructor
public class MovieOmdbDtoController {

    private ModelMapper modelMapper;
    private OmdbClientDTOImpl omdbClient;
    private MovieService movieService;
    private JobLauncher jobLauncher;
    private Job job;

    @Async
    @GetMapping("/search/{title}")
    public void searchAndSaveMovies(@PathVariable("title") String title) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addString("title", title);
        parametersBuilder.addLong("time", System.currentTimeMillis());
        JobParameters jobParameters = parametersBuilder.toJobParameters();

        jobLauncher.run(job, jobParameters);
    }

    @GetMapping("/search/imdb-id/{id}")
    public MovieDto searchAndSaveMovieByImdbId(@PathVariable("id") String id) {
        OmdbMovie omdbMovie = omdbClient.searchByImdbId(id);
        MovieDto response = modelMapper.map(omdbMovie, MovieDto.class);
        return movieService.save(response);
    }
}