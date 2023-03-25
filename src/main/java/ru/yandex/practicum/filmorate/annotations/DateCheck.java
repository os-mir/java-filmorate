package ru.yandex.practicum.filmorate.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = {IsDateValid.class})
public @interface DateCheck {
    String message() default "Тогда ещё не снимали кино";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
