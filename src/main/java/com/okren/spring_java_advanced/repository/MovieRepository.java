package com.okren.spring_java_advanced.repository;

import com.okren.spring_java_advanced.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);  // даний метод реалізований самим спрінгом (при правильній назві)
    //  чи за тайтлом є такий об'єкт

}

