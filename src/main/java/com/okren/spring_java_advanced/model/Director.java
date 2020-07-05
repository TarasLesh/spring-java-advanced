package com.okren.spring_java_advanced.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

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

}
