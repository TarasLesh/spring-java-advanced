package com.okren.spring_java_advanced.service;

import com.okren.spring_java_advanced.model.Movie;
import com.okren.spring_java_advanced.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie insertMovie(Movie movie) {
//        if (movieRepository.findByTitle(movie.getTitle()) == null) {   // перевіряємо на унікальність даних, які записуються
        if (!movieRepository.findByTitle(movie.getTitle()).isPresent()) {   // перевіряємо на унікальність даних, які записуються
            return movieRepository.save(movie);
        } else {
            log.info("Movie with title "+movie.getTitle()+" already exists in DB!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This movie title already exists!");
        }
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
