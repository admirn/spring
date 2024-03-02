package com.admirnurkovic.bookStore.services.impl;

import com.admirnurkovic.bookStore.domain.entities.BookEntity;
import com.admirnurkovic.bookStore.repositories.BookRepository;
import com.admirnurkovic.bookStore.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<BookEntity> getSingleBook(String isbn) {
        return bookRepository.findById(isbn);
    }
}
