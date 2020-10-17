package com.example.DigitalBankAPI.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BeforeDateValidator implements ConstraintValidator<BeforeDate, LocalDate> {

    private int years;

    @Override
    public void initialize(BeforeDate constraintAnnotation) {
        years = constraintAnnotation.years();
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) {
            return false;
        }

        return ChronoUnit.YEARS.between(date, LocalDate.now()) >= years;

    }
}
