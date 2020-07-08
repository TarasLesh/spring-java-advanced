package com.okren.spring_java_advanced.service;

import com.okren.spring_java_advanced.dtos.MovieDTO;
import com.okren.spring_java_advanced.model.Movie;
import org.springframework.data.domain.PageRequest;

public interface IMovieService {

    void deleteMovie(Integer id);

    MovieDTO getMovies(PageRequest pageRequest);

    Movie insertMovie(Movie movie, int directorId);

    Movie updateMovie(Movie movie);
}
