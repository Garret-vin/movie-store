package com.garret.movies.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class MovieOmdbController {

    private JobLauncher jobLauncher;
    private Job job;

    @Async
    @PostMapping("/search/{title}")
    public void searchMovies(@PathVariable("title") String title)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addString("title", title);
        parametersBuilder.addLong("time", System.currentTimeMillis());
        JobParameters jobParameters = parametersBuilder.toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        log.info("Job execution status: " + jobExecution.getStatus());
    }
}
