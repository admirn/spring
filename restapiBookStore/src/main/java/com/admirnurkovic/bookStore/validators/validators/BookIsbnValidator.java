package com.admirnurkovic.bookStore.validators.validators;

import com.admirnurkovic.bookStore.services.BookService;
import com.admirnurkovic.bookStore.validators.annotations.ValidBookIsbn;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BookIsbnValidator implements ConstraintValidator<ValidBookIsbn, String> {

    private final BookService bookService;

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        if(isbn == null || isbn.trim().isEmpty() || bookService.getSingleBook(isbn).isPresent()){
            return false;
        }

        return isValidISBN(isbn);
    }

    public static boolean isValidISBN(String isbn) {
        isbn = isbn.replaceAll("[\\s\\-]+", "");

        return isbn.length() == 10 || isbn.length() == 13;
    }
}
