package com.okren.spring_java_advanced.controller;

import com.okren.spring_java_advanced.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 // RestController (на відміну від Controller) бере дані, які ми повертаємо, та перетвоює в JSON
 //          і автоматично вішає анотацію @ResponseBody
@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();

    {
        movies.add(new Movie(1, "The Gentlemen", "Directed by Guy Ritchie"));
        movies.add(new Movie(2, "Harry Potter", "1th movie HP"));
    }

    // RequestMapping для реагування на HTTP Request
    @RequestMapping(method = RequestMethod.GET)

//  public @ResponseBody // анотація ResponseBody конвертувала дані в JSON
    public List<Movie> getMovies() {
        return movies;
    }


    @RequestMapping(method = RequestMethod.POST)  //  /movies/add   якщо додати   , path = "/add"
    @ResponseStatus(HttpStatus.CREATED)
//      public  ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
    public Movie createMovie(@RequestBody Movie movie) {
        movies.add(movie);
//        return ResponseEntity.accepted().body(movie);
        return movie;
    }
}
