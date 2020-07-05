package com.okren.spring_java_advanced.repository;

import com.okren.spring_java_advanced.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
