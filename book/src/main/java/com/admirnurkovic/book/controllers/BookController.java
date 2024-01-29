package com.admirnurkovic.book.controllers;

import com.admirnurkovic.book.domain.Book;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {


    @GetMapping(path = "/books")
    public Book retrieveBook(){
        return Book.builder()
                .isbn("733-7333-733")
                .title("Simple Book")
                .author("Admir Nurkovic")
                .yearPublished("1989")
                .build();
    }

    @PostMapping(path = "/books")
    public Book createBook(@RequestBody final Book book){
        log.info(book.toString());
        return book;
    }
}
