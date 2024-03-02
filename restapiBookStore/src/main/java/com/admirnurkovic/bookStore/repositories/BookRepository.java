package com.admirnurkovic.bookStore.repositories;

import com.admirnurkovic.bookStore.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {

}
