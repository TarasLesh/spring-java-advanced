package com.okren.spring_java_advanced.service;

import com.okren.spring_java_advanced.model.Director;

import java.util.List;

public interface IDirectorService {

    Director insertDirector(Director director);

    List<Director> getAllDirectors();
}
