package com.admirnurkovic.DatabaseConfig.services.impl;

import com.admirnurkovic.DatabaseConfig.domain.entities.BookEntity;
import com.admirnurkovic.DatabaseConfig.repositories.BookRepository;
import com.admirnurkovic.DatabaseConfig.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public BookEntity saveBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).toList();
    }
}
