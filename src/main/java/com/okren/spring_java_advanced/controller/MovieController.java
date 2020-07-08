package com.okren.spring_java_advanced.controller;

import com.okren.spring_java_advanced.dtos.MovieDTO;
import com.okren.spring_java_advanced.model.Movie;
import com.okren.spring_java_advanced.service.IMovieService;
import com.okren.spring_java_advanced.validation.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// RestController (на відміну від Controller) бере дані, які ми повертаємо, та перетвоює в JSON
//          і автоматично вішає анотацію @ResponseBody
@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
//    private MovieRepository movieRepository;  //  було поки не створили МувіСервіс рівень
    private IMovieService movieService;

    @Autowired
    private MovieValidator movieValidator;


    //    public MovieController(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;

//    {
//        System.out.println("i am here " + movieRepository.count());
//        System.out.println("i am here " + movieRepository.findAll());
//    }


//    private List<Movie> movies = new ArrayList<>();
//
//    {
//        movies.add(new Movie(1, "The Gentlemen", "Directed by Guy Ritchie"));
//        movies.add(new Movie(2, "Harry Potter", "1th movie HP"));
//    }

    // RequestMapping для реагування на HTTP Request
    @RequestMapping(method = RequestMethod.GET)

//  public @ResponseBody // анотація ResponseBody конвертувала дані в JSON
    public MovieDTO getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return movieService.getMovies(pageRequest);

//        System.out.println("i am here " + movieRepository.count());
//        System.out.println("i am here " + movieRepository.findAll());
    }
//        public List<Movie> getMovies() { return movies; }  // було без анотації @Autowired

    @RequestMapping(method = RequestMethod.POST, value = "/{directorId}")  //  /movies/add   якщо додати   , path = "/add"
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody @Valid Movie movie, @PathVariable int directorId) {
        return movieService.insertMovie(movie, directorId);
        //      public  ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        //        movies.add(movie);   // було без анотації @Autowired

//        return ResponseEntity.accepted().body(movie);

//        return movie;
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
        return movieService.updateMovie(movie);
//        return movie;

//        if (movieFromList == null){
//            movies.add(movie);
//        } else {
//            movies.set(id, movie);
//        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Integer id) { //, @RequestParam(value = "test") String test1){  // RequestParam ..url?test=some_param

//        System.out.println(test1);
//                Optional<Movie> movieOptional = movies.stream()
//                        .filter(movie1 -> movie1.getId() == id)
//                        .findFirst();
//                movieOptional.ifPresent(movie -> movies.remove(movie));   // було без анотації @Autowired

//        Optional<Movie> movieOptional = movies.stream()
//                .filter(movie1 -> movie1.getId() == id)
//                .findFirst();

//        if (movieRepository.existsById(id)) {
//            movieRepository.deleteById(id);
//            System.out.println("ID Deleted");
//        } 

        movieService.deleteMovie(id);

    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(movieValidator);   // добавляємо новий Валідатор
    }


}
