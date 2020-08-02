package com.okren.spring_java_advanced.controller;

import com.okren.spring_java_advanced.dtos.MovieDTO;
import com.okren.spring_java_advanced.model.Movie;
import com.okren.spring_java_advanced.service.IMovieService;
import com.okren.spring_java_advanced.validation.MovieValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.util.Arrays;

@EnableAutoConfiguration(exclude = {   // вказуємо, які Конфігураційні класи щоб були "заексклудені" (вимкнені, вбиті)
        HibernateJpaAutoConfiguration.class,  // клас, який відповідає за створення реальних класів під наші repository-інтерфейси (всім, що потрібно для Hibernate та JPA)
        DataSourceTransactionManagerAutoConfiguration.class,  // клас, який налагоджує connection до бази, тобто створює наш entity-manager (transaction-manager )
        DataSourceAutoConfiguration.class, // клас, який робить connection до бази даних
        })
@WebMvcTest(MovieController.class) // вказуємо, який контроллер тестуємо
@ContextConfiguration(classes = MovieController.class)  // все конфігурне - ні, тільки цей клас
public class MovieControllerTest {

    @MockBean
    private IMovieService movieService;

    @MockBean
    private MovieValidator movieValidator;

    @Autowired
    private MovieController movieController;  // Клас, який ми тестуємо, ми його завжди @Autowired

    @Autowired
    private MockMvc mockMvc;  // необхідно для того, щоб зробити request

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();  // mockMvc буде тепер клієнтом для MovieController. Буде знати, на які request реагує MovieController. Інший контроллер вже не зможемо заюзати.
    }

    @Test
    public void getMoviesWithoutParametersMustRespondWithMovieList() throws Exception {

        Movie movie = new Movie();
        movie.setId(1);  // створили Movie і передали йому id, оскільки більше нічого не перевіряємо.
        movie.setDuration(125); // не працювало через JsonIgnore
        // Більш правильно заповнити ці об'єкти (movie i movieDTO), бо можливо будемо перевіряти щось більше.
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTotalPages(1);
        movieDTO.setMovies(Arrays.asList(movie));


        BDDMockito.given(movieService.getMovies(ArgumentMatchers.any(PageRequest.class)))  // коли ми викликаємо movieService.getMovies з будь-якими параметрами в pageRequest (тобто будь-який об'єкт класу PageRequest), то..
        .willReturn(movieDTO);  // повертає нам movieDTO (як в методі getMovies на MovieService)

//        Mockito.when(movieService.getMovies(ArgumentMatchers.any(PageRequest.class))).thenReturn(movieDTO); // через Mockito

//        MovieDTO movies = movieService.getMovies();
//        Assertions.assertThat(movies.getTotalPages()).isEqualTo(2); // так би писали (в mockMvc), якби робили test на MovieService

        mockMvc.perform(MockMvcRequestBuilders.get("/movies").accept(MediaType.APPLICATION_JSON))  // в perform говоримо, що маємо робити: Через MockMvcRequestBuilders вказуємо що метод GET і url "/movies". Далі можемо говорити що ми передаємо в request
                .andExpect(MockMvcResultMatchers.status().isOk()) // очікуємо, що статус буде ОК
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].id").value(1)) // передаємо, як наш JSON має виглядати (id нульового елемента movies має бути 1)
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].duration").value(125)) // duration нульового елемента movies має бути 125)
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1)); // очікуємо, що totalPages буде 1
    }
}
