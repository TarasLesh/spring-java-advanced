package com.okren.spring_java_advanced.repository;

import com.okren.spring_java_advanced.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
