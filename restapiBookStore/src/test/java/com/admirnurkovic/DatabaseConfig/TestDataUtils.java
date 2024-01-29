package com.admirnurkovic.DatabaseConfig;

import com.admirnurkovic.DatabaseConfig.domain.entities.AuthorEntity;
import com.admirnurkovic.DatabaseConfig.domain.entities.BookEntity;

public final class TestDataUtils {

    private TestDataUtils(){}

    public static AuthorEntity creteTestAuthor() {
        return AuthorEntity.builder()
                .id(1L)
                .age(80)
                .name("Admir Nurkovic")
                .build();
    }

    public static BookEntity createSimpleBook(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345")
                .title("The shadow in the Athic")
                .authorEntity(authorEntity)
                .build();
    }

    public static AuthorEntity creteTestAuthorWithParams(Long id, Integer age, String name ) {
        return AuthorEntity.builder()
                .id(id)
                .age(age)
                .name(name)
                .build();
    }

    public static BookEntity createSimpleBookWithParams(String isbn, String title, AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn(isbn)
                .title(title)
                .authorEntity(authorEntity)
                .build();
    }
}
