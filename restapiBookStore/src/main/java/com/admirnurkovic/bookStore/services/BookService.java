package com.admirnurkovic.bookStore.services;

import com.admirnurkovic.bookStore.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity saveBook(String isbn, BookEntity book);
    List<BookEntity> getAllBooks();

    Optional<BookEntity> getSingleBook(String isbn);
}
