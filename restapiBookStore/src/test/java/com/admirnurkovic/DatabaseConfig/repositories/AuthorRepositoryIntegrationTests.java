package com.admirnurkovic.DatabaseConfig.repositories;

import com.admirnurkovic.DatabaseConfig.TestDataUtils;
import com.admirnurkovic.DatabaseConfig.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {

    private final AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }
    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtils.creteTestAuthor();
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRetrieved(){
        AuthorEntity authorEntity1 = TestDataUtils.creteTestAuthorWithParams(1L, 35, "Samy Simple");
        underTest.save(authorEntity1);
        AuthorEntity authorEntity2 = TestDataUtils.creteTestAuthorWithParams(2L, 30, "Stony Simple");
        underTest.save(authorEntity2);
        AuthorEntity authorEntity3 = TestDataUtils.creteTestAuthorWithParams(3L, 32, "Greedy Simple");
        underTest.save(authorEntity3);

        Iterable<AuthorEntity> result = underTest.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactly(authorEntity1, authorEntity2, authorEntity3);
    }
//
    @Test
    public void testThatAuthorCanBeUpdated(){
        AuthorEntity authorEntity1 = TestDataUtils.creteTestAuthor();
        underTest.save(authorEntity1);
        authorEntity1.setName("Updated");
        underTest.save(authorEntity1);

        Optional<AuthorEntity> result = underTest.findById(authorEntity1.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity1);

    }
//
    @Test
    public void testAuthorCanBeDeleted(){
        AuthorEntity authorEntity = TestDataUtils.creteTestAuthor();
        underTest.save(authorEntity);

//        Book book = TestDataUtils.createSimpleBook(author);

        underTest.delete(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isEmpty();
//       assertThat(book).isNull();

    }

    @Test
    public void testThatAuthorWithAgeLessThen(){
        AuthorEntity authorEntity1 = TestDataUtils.creteTestAuthorWithParams(
                1L, 30, "Admir"
        );
        underTest.save(authorEntity1);
        AuthorEntity authorEntity2 = TestDataUtils.creteTestAuthorWithParams(
                2L, 35, "Test 2"
        );
        underTest.save(authorEntity2);
        AuthorEntity authorEntity3 = TestDataUtils.creteTestAuthorWithParams(
                3L, 40, "Test 3"
        );
        underTest.save(authorEntity3);

        Iterable<AuthorEntity> authors = underTest.ageLessThan(40);

        assertThat(authors).containsExactly(authorEntity1, authorEntity2);
    }
    @Test
    public void testAuthorWithAgeGreaterThe(){
        AuthorEntity authorEntity1 = TestDataUtils.creteTestAuthorWithParams(
                1L, 30, "Admir"
        );
        underTest.save(authorEntity1);
        AuthorEntity authorEntity2 = TestDataUtils.creteTestAuthorWithParams(
                2L, 35, "Test 2"
        );
        underTest.save(authorEntity2);
        AuthorEntity authorEntity3 = TestDataUtils.creteTestAuthorWithParams(
                3L, 40, "Test 3"
        );
        underTest.save(authorEntity3);

        Iterable<AuthorEntity> result = underTest.ageGreaterThen(30);
        assertThat(result).containsExactly(authorEntity2, authorEntity3);
    }
}
