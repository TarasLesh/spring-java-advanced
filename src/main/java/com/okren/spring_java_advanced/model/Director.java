package com.okren.spring_java_advanced.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @JsonFormat(pattern = "dd-MM-yyyy") // необхідно вказувати формат для дати.  mm - minutes, HH - hours, SS - seconds, sss - mil-seconds
    private LocalDate birthDate;   // новий клас для роботи з датами (пакет java.time). Ще є LocalDateTime, LocalTime

    //  робимо bidirectional зв'язок
    @OneToMany(targetEntity = Movie.class, mappedBy = "director")
//    @OneToMany(targetEntity = Movie.class)
    @JoinColumn(name = "director_id", insertable = false, updatable = false, nullable = false)  // в Movie створиться поле director_id
    private List<Movie> movies;
}

