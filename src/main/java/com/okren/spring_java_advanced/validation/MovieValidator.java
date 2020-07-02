package com.okren.spring_java_advanced.validation;

import com.okren.spring_java_advanced.model.Movie;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MovieValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {     // перевіряє на який клас вона має спиратись
        return Movie.class.isAssignableFrom(aClass);  // чи обєкт класу, який прийшов в aClass підпадає під обєкт Movie. Це як метод equals() або isInstanceOf(). aClass може бути батьківський
    }

    @Override
    public void validate(Object target, Errors errors) {
        Movie movie = (Movie) target;  // target кастуємо в Movie. Ми знаємо що прийшов Movie, перевірили це в методі supports
        if (!StringHelper.isNullOrEmptyString(movie.getTitle())) {   // дивимось чи стічка не пуста і не null
            char firstChar = movie.getTitle().charAt(0);   // берем перший символ
            if (firstChar < 65 || firstChar > 90) {    // великі букви з ASCII таблиці
                errors.rejectValue("title", "movie.title.capital-letter", "First letter in title movie should be CAPITAL!"); // поле з яким помилка, Error-code(?), опис помилки
                // rejectValue - якщо помилка з одним полем об'єкту, reject - якщо помилка зі всім об'єктом.
            }
        }   // else { errors.rejectValue( стрічка не має бути пуста ) }
    }
}
