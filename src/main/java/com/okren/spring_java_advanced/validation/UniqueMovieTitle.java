package com.okren.spring_java_advanced.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)   //  анотація працює в рантаймі
@Target(ElementType.FIELD)    // ціль - поля, тобто анотація проацює по полях
@Constraint(validatedBy = UniqueMovieTitleValidator.class)  // зазначаємо яким класом має валідуватись
public @interface UniqueMovieTitle {

    String message() default "Movie title should be unique";  // message
    Class<?>[] groups() default {};                    //   ?
    Class<? extends Payload>[] payload() default {};   //   ?

}
