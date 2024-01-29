package com.admirnurkovic.DatabaseConfig.controllers;

import com.admirnurkovic.DatabaseConfig.domain.dto.BookDto;
import com.admirnurkovic.DatabaseConfig.domain.entities.BookEntity;
import com.admirnurkovic.DatabaseConfig.mappers.Mapper;
import com.admirnurkovic.DatabaseConfig.services.BookService;
import com.admirnurkovic.DatabaseConfig.services.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;
    private Mapper<BookEntity, BookDto> bookMapper;

   public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper ){
       this.bookService = bookService;
       this.bookMapper = bookMapper;
   }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn,
                                              @RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity resultBook = bookService.saveBook(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(resultBook), HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<BookEntity>> getAllBooks(){
       List<BookEntity> list = bookService.getAllBooks();
       return new ResponseEntity<>(list, HttpStatus.FOUND);
    }





}
