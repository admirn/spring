package com.admirnurkovic.DatabaseConfig.services.impl;

import com.admirnurkovic.DatabaseConfig.domain.entities.AuthorEntity;
import com.admirnurkovic.DatabaseConfig.repositories.AuthorRepository;
import com.admirnurkovic.DatabaseConfig.services.AuthorService;
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
    public boolean isExist(Long id){
       return authorRepository.existsById(id);
    }
}
