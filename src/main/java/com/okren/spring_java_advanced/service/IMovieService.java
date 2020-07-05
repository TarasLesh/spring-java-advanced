package com.okren.spring_java_advanced.service;

import com.okren.spring_java_advanced.model.Movie;

import java.util.List;

public interface IMovieService {

    void deleteMovie(Integer id);

    List<Movie> getAllMovies();

    Movie insertMovie(Movie movie, int directorId);

    Movie updateMovie(Movie movie);
}
