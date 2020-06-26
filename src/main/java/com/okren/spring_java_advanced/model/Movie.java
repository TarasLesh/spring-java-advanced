package com.okren.spring_java_advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Movie {

    private int id;
    private String title;
    private String description;
}
