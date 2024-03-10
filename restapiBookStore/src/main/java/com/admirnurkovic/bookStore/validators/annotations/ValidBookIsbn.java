package com.admirnurkovic.bookStore.validators.annotations;

import com.admirnurkovic.bookStore.validators.validators.BookIsbnValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookIsbnValidator.class)
public @interface ValidBookIsbn {
    String message() default "Invalid isbn pattern. It should either has 10 or 13 numeric characters.";
    Class<?>[] groups() default  {};

    Class<? extends Payload>[] payload() default {};
}
