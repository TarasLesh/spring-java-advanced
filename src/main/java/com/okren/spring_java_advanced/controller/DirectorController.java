package com.okren.spring_java_advanced.controller;

import com.okren.spring_java_advanced.model.Director;
import com.okren.spring_java_advanced.service.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/director")
public class DirectorController {

    @Autowired
    private IDirectorService directorService;

    @GetMapping
    public List<Director> getDirectors(){
        return directorService.getAllDirectors();
    }

    @PostMapping()
    public Director createDirector(@RequestBody Director director){
        return directorService.insertDirector(director);
    }
}
