package com.admirnurkovic.DatabaseConfig.services;

import com.admirnurkovic.DatabaseConfig.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity saveBook(String isbn, BookEntity book);
    List<BookEntity> getAllBooks();

    Optional<BookEntity> getSingleBook(String isbn);
}
