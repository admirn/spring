package com.admirnurkovic.DatabaseConfig.services;

import com.admirnurkovic.DatabaseConfig.domain.entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity saveBook(String isbn, BookEntity book);
    List<BookEntity> getAllBooks();
}
