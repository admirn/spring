package com.admirnurkovic.DatabaseConfig.controllers;

import com.admirnurkovic.DatabaseConfig.domain.entities.AuthorEntity;
import com.admirnurkovic.DatabaseConfig.domain.dto.AuthorDto;
import com.admirnurkovic.DatabaseConfig.mappers.Mapper;
import com.admirnurkovic.DatabaseConfig.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {


    private final AuthorService authorService;

    private final Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }


    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthor = authorService.save(authorEntity);
        return  new ResponseEntity<>(authorMapper.mapTo(savedAuthor), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> getAllAuthors(){
        List<AuthorEntity> list = authorService.getAllAuthors();
        return list.stream().map(authorMapper::mapTo)
                .collect(Collectors.toList());

    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id){
        Optional<AuthorEntity> responseAuthorEntity = authorService.getAuthor(id);
        return responseAuthorEntity.map(authorEntity -> {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.FOUND);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(
            @PathVariable("id") Long id,
            @RequestBody AuthorDto authorDto){

        if(!authorService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorDto.setId(id);
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.OK);
    }
}
