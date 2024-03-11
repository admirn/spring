package com.admirnurkovic.bookStore.services;

import com.admirnurkovic.bookStore.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createUpdateBook(String isbn, BookEntity book);
    List<BookEntity> getAllBooks();

    Page<BookEntity> getAllBooks(Pageable pageable);

    Optional<BookEntity> getSingleBook(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
