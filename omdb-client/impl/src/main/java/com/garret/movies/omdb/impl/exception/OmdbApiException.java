package com.garret.movies.omdb.impl.exception;

public class OmdbApiException extends RuntimeException {
    public OmdbApiException(String message) {
        super(message);
    }
}
