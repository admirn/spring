package com.admirnurkovic.bookStore.validators.validators;

import com.admirnurkovic.bookStore.validators.annotations.ValidCountryCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CountryCodeValidator implements ConstraintValidator<ValidCountryCode, String> {

    private static final List<String> VALID_COUNTRY_CODES = List.of("US", "CA", "DE", "GB", "MNE");
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return  VALID_COUNTRY_CODES.contains(s);
    }
}
