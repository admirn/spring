package com.admirnurkovic.bookStore.controllers;

import com.admirnurkovic.bookStore.domain.dto.AuthorDto;
import com.admirnurkovic.bookStore.domain.dto.BookDto;
import com.admirnurkovic.bookStore.domain.entities.BookEntity;
import com.admirnurkovic.bookStore.mappers.Mapper;
import com.admirnurkovic.bookStore.services.BookService;
import com.admirnurkovic.bookStore.validators.groups.BookPatchValidationGroup;
import com.admirnurkovic.bookStore.validators.groups.BookPostValidationGroup;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final Mapper<BookEntity, BookDto> bookMapper;

   public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper ){
       this.bookService = bookService;
       this.bookMapper = bookMapper;
   }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn,
                                               @Validated(BookPostValidationGroup.class) @RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookexists = bookService.isExists(isbn);
        BookEntity resultBook = bookService.createUpdateBook(isbn, bookEntity);
        BookDto saveUpdateBook = bookMapper.mapTo(resultBook);


        if(bookexists){
            //update
            return new ResponseEntity<>(saveUpdateBook, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(saveUpdateBook, HttpStatus.CREATED);
        }


    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllBooks(Pageable pagable){
       Page<BookEntity> list = bookService.getAllBooks(pagable);
       return new ResponseEntity<>(list.map(bookMapper::mapTo), HttpStatus.OK);
    }

    @GetMapping(path="/{isbn}")
    public ResponseEntity<BookDto> getSingleBook(@PathVariable("isbn") String isbn){
        Optional<BookEntity> book = bookService.getSingleBook(isbn);
        return book.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.FOUND);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(
            @PathVariable("isbn") String isbn,
            @Validated(BookPatchValidationGroup.class) @RequestBody BookDto bookDto
    ){
       if(!bookService.isExists(isbn)){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       if(bookDto.getAuthorEntity() != null && bookDto.getAuthorEntity().getId() == null){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }


       BookEntity bookEntity = bookMapper.mapFrom((bookDto));
       BookEntity updatedBook = bookService.partialUpdate(isbn, bookEntity);
       return new ResponseEntity<>(bookMapper.mapTo(updatedBook), HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn){
       bookService.delete(isbn);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
