package com.okren.spring_java_advanced.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okren.spring_java_advanced.validation.UniqueMovieTitle;
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
    @JsonIgnore     // поле буде ігноруватись. Не буде відображатись в Response (не відправлятись на клієнт) і не буде хотіти щоб його заповнили.
    private int id;
    @Column(length = 355, nullable = false) // зміна довжити на 355 (замість 255 для varchar), і не NonNull
    @NotBlank   //  не пуста стрічка, або не заповнена пробілом
    @UniqueMovieTitle  // власний валідатор
    private String title;
    @NotBlank
    private String description;
    @Positive   //  додатнє значення
    private int duration;

    @ManyToOne(targetEntity = Director.class, optional = false, cascade = CascadeType.PERSIST)  // Movie дивиться на Director. Мене багато - він один.
    // JoinColumn in that case is optional
//    @JoinColumn(name = "director_id", insertable = false, updatable = false, nullable = false)  // говорить до якої колонки підключатись (як має називатись колонка (зовнішній ключ) на Movie), і ми не можемо мінять будь-яким чином
    @JsonIgnore  // не буде надсилатись director разом з movie
    private Director director;

}
