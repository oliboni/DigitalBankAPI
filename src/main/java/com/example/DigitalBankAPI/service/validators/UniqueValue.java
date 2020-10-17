package com.example.DigitalBankAPI.service.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { UniqueValueValidator.class })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueValue {
    String message() default "{com.example.DigitalBankAPI.service.validators}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}