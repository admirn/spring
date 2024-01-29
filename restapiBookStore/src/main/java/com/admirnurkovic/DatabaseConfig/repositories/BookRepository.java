package com.admirnurkovic.DatabaseConfig.repositories;

import com.admirnurkovic.DatabaseConfig.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, String> {

}
