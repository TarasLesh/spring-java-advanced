package com.okren.spring_java_advanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity    // говорить що з полей класу будуть створені поля в таблиці бази даних
public class Movie {
    @Id  // id в таблиці. вішається над полем, яке буде ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // автогенерація ID
    private int id;
    @Column(length = 355, nullable = false) // зміна довжити на 355 (замість 255 для varchar), і не NonNull
    @NotBlank   //  не пуста стрічка, або не заповнена пробілом
    private String title;
    @NotBlank
    private String description;
    @Positive   //  додатнє значення
    private int duration;

}
