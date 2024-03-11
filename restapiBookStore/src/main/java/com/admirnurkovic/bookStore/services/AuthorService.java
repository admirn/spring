package com.admirnurkovic.bookStore.services;

import com.admirnurkovic.bookStore.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);
    List<AuthorEntity> getAllAuthors();

    Optional<AuthorEntity> getAuthor(Long id);

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
