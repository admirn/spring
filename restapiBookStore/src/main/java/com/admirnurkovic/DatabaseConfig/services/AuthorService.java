package com.admirnurkovic.DatabaseConfig.services;

import com.admirnurkovic.DatabaseConfig.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);
    List<AuthorEntity> getAllAuthors();

    Optional<AuthorEntity> getAuthor(Long id);

    boolean isExist(Long id);
}
