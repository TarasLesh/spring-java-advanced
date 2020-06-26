package com.okren.spring_java_advanced.controller;

import com.okren.spring_java_advanced.model.Movie;
import com.okren.spring_java_advanced.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// RestController (на відміну від Controller) бере дані, які ми повертаємо, та перетвоює в JSON
//          і автоматично вішає анотацію @ResponseBody
@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;  //

    private List<Movie> movies = new ArrayList<>();

    {
        movies.add(new Movie(1, "The Gentlemen", "Directed by Guy Ritchie"));
        movies.add(new Movie(2, "Harry Potter", "1th movie HP"));
    }

    // RequestMapping для реагування на HTTP Request
    @RequestMapping(method = RequestMethod.GET)

//  public @ResponseBody // анотація ResponseBody конвертувала дані в JSON
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
    //    public List<Movie> getMovies() { return movies; }  // було без анотації @Autowired


    @RequestMapping(method = RequestMethod.POST)  //  /movies/add   якщо додати   , path = "/add"
    @ResponseStatus(HttpStatus.CREATED)
//      public  ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
    public Movie createMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        //        movies.add(movie);   // було без анотації @Autowired

//        return ResponseEntity.accepted().body(movie);
        return movie;
    }

    //                       movies/id
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)   // в PATCH можна надсилати лише одне поле
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable int id) {
//        Optional<Movie> movieOptional = movies.stream()
//                .filter(movie1 -> movie1.getId() == id)
//                .findFirst();
//
//        if (movieOptional.isPresent()) {
//            Movie movieFromList = movieOptional.get();
//            movies.set(movies.indexOf(movieFromList), movie);
//
//        } else {
//            movie.setId(id);
//            movies.add(movie);               // було без анотації @Autowired
//        }
//        return movie;

        movie.setId(id);
        movieRepository.save(movie);
        return movie;

//        if (movieFromList == null){
//            movies.add(movie);
//        } else {
//            movies.set(id, movie);
//        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Integer id){ //, @RequestParam(value = "test") String test1){  // RequestParam ..url?test=some_param

//        System.out.println(test1);
        //        Optional<Movie> movieOptional = movies.stream()
        //                .filter(movie1 -> movie1.getId() == id)
        //                .findFirst();
        //        movieOptional.ifPresent(movie -> movies.remove(movie));   // було без анотації @Autowired

        movieRepository.deleteById(id);
    }
}
