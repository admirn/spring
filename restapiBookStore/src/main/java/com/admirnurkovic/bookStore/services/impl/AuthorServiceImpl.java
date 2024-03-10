package com.admirnurkovic.bookStore.services.impl;

import com.admirnurkovic.bookStore.domain.entities.AuthorEntity;
import com.admirnurkovic.bookStore.repositories.AuthorRepository;
import com.admirnurkovic.bookStore.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Optional<AuthorEntity> getAuthor(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id){
       return authorRepository.existsById(id);
    }

    @Override
    public  AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

        return authorRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            Optional.ofNullable(authorEntity.getCountryCode()).ifPresent(existingAuthor::setCountryCode);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author does not exist"));
    }
}
