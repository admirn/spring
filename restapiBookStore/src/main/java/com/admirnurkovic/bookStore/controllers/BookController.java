package com.admirnurkovic.bookStore.controllers;

import com.admirnurkovic.bookStore.domain.dto.BookDto;
import com.admirnurkovic.bookStore.domain.entities.BookEntity;
import com.admirnurkovic.bookStore.mappers.Mapper;
import com.admirnurkovic.bookStore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<BookDto>> getAllBooks(){
       List<BookEntity> list = bookService.getAllBooks();
       return new ResponseEntity<>(list.stream()
               .map(bookMapper::mapTo)
               .collect(Collectors.toList()), HttpStatus.FOUND);
    }

    @GetMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> getSingleBook(@PathVariable("isbn") String isbn){
        Optional<BookEntity> book = bookService.getSingleBook(isbn);
        return book.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.FOUND);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }





}
