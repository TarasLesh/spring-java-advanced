package com.okren.spring_java_advanced.validation;

import com.okren.spring_java_advanced.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// анотацію @Component не вішаємо, тому що Spring усі кліси, які implements ConstraintValidator автоматично робить бінами
public class UniqueMovieTitleValidator implements ConstraintValidator<UniqueMovieTitle, String> {

    // DO NOT USE SPRING BEANS IN VALIDATORS!
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {

        return true; //changen

//        return title != null && movieRepository.findByTitle(title) == null;
    }
}
