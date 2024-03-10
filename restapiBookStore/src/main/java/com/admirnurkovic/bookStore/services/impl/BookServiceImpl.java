package com.admirnurkovic.bookStore.services.impl;

import com.admirnurkovic.bookStore.domain.entities.AuthorEntity;
import com.admirnurkovic.bookStore.domain.entities.BookEntity;
import com.admirnurkovic.bookStore.repositories.AuthorRepository;
import com.admirnurkovic.bookStore.repositories.BookRepository;
import com.admirnurkovic.bookStore.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @Override
    public BookEntity createUpdateBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).toList();
    }
    @Override
    public Page<BookEntity> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> getSingleBook(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        log.info(String.valueOf(bookEntity));
        if(bookEntity.getAuthorEntity() != null && bookEntity.getAuthorEntity().getId() != null){
            AuthorEntity author = authorRepository.findById(bookEntity.getAuthorEntity().getId()).orElseThrow(
                    () -> new RuntimeException("Author does not exist")
            );
            bookEntity.setAuthorEntity(author);
            log.info(String.valueOf(author));
            return bookRepository.findById(isbn).map(existingBook -> {
                Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
                Optional.ofNullable(author).ifPresent(existingBook::setAuthorEntity);
                return bookRepository.save(bookEntity);
            }).orElseThrow(() -> new RuntimeException("Book does not exist"));
        }


        return bookRepository.findById(isbn).map(existingBook -> {
             Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
             return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book does not exist"));
    }
}
