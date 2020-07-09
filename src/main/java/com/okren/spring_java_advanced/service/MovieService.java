package com.okren.spring_java_advanced.service;

import com.okren.spring_java_advanced.dtos.MovieDTO;
import com.okren.spring_java_advanced.model.Movie;
import com.okren.spring_java_advanced.repository.DirectorRepository;
import com.okren.spring_java_advanced.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO getMovies(PageRequest pageRequest) {  // При List<Movie> отримаємо     "content": , "pageable": , "sort": ,  "totalPages": 4,   "totalElements": 12,   "last": false, .. Для того щоб ці всі дані не передавати на FrontEnd:

        Page<Movie> moviePages = movieRepository.findAll(pageRequest);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovies(moviePages.getContent());
        movieDTO.setTotalPages(moviePages.getTotalPages());
        movieDTO.setPagesCount(moviePages.getTotalPages());
        movieDTO.setEmpty(moviePages.isEmpty());
        movieDTO.setLast(moviePages.isLast());
        return movieDTO;
    }

    @Override
    public Movie insertMovie(Movie movie, int directorId) {
//        if (movieRepository.findByTitle(movie.getTitle()) == null) {   // перевіряємо на унікальність даних, які записуються
        if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {   // перевіряємо на унікальність даних, які записуються
            log.info("Movie with title " + movie.getTitle() + " already exists in DB!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This movie title already exists!");
        }
        directorRepository.findById(directorId).ifPresent(director -> {
                    movie.setDirector(director);
                    movieRepository.save(movie);
                });
        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
