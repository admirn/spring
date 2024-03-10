package com.admirnurkovic.bookStore.validators.annotations;

import com.admirnurkovic.bookStore.validators.validators.AuthorValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthorValidator.class)
public @interface ValidAuthor {
    String message() default "Author id is either undefined or does not exist in db";
    Class<?>[] groups() default  {};

    Class<? extends Payload>[] payload() default {};
}
