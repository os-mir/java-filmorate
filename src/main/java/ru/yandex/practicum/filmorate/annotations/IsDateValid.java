package ru.yandex.practicum.filmorate.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class IsDateValid implements ConstraintValidator<DateCheck, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value.isBefore(LocalDate.of(1895, 12, 28))) {
            return false;
        }
        return true;
    }

}
