package com.admirnurkovic.bookStore.repositories;

import com.admirnurkovic.bookStore.TestDataUtils;
import com.admirnurkovic.bookStore.domain.entities.AuthorEntity;
import com.admirnurkovic.bookStore.domain.entities.BookEntity;
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
public class BookEntityRepositoryIntegrationTests {

    private BookRepository underTest;
    private AuthorRepository authorDao;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorDao){
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testBookCreationAndRetrival(){

        AuthorEntity authorEntity = TestDataUtils.creteTestAuthor();
        BookEntity bookEntity = TestDataUtils.createSimpleBook(authorEntity);
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testMultipleBookCreationandFindMany(){
        AuthorEntity authorEntity = TestDataUtils.creteTestAuthor();
        authorDao.save(authorEntity);

        AuthorEntity authorEntity1 = TestDataUtils.creteTestAuthorWithParams(2L, 35, "George Orwell");
        authorDao.save(authorEntity1);

        BookEntity bookEntity1 = TestDataUtils.createSimpleBookWithParams("253-253-656", "The oldman and the sea", authorEntity);
        underTest.save(bookEntity1);
        BookEntity bookEntity2 = TestDataUtils.createSimpleBookWithParams(
                "355-355-355", "Gatsby", authorEntity
        );
        underTest.save(bookEntity2);
        Iterable<BookEntity> result = underTest.findAll();

        assertThat(result).hasSize(2).containsExactly(bookEntity1, bookEntity2);
        for(BookEntity bk: result){
            assertThat(bk.getAuthorEntity()).isEqualTo(authorEntity);
        }

    }
    @Test
    public void testThatBookCanBeUpdated(){
        AuthorEntity authorEntity = TestDataUtils.creteTestAuthor();
        authorDao.save(authorEntity);

        BookEntity bookEntity = TestDataUtils.createSimpleBook(authorEntity);

        underTest.save(bookEntity);

        bookEntity.setTitle("UPDATED");
        underTest.save(bookEntity);

        Optional<BookEntity> one = underTest.findById(bookEntity.getIsbn());

        assertThat(one).isPresent();
        assertThat(one.get()).isEqualTo(bookEntity);


    }
//
    @Test
    public void testThatBookCanBeDeleted(){
        AuthorEntity authorEntity = TestDataUtils.creteTestAuthor();
        authorDao.save(authorEntity);
        BookEntity bookEntity = TestDataUtils.createSimpleBook(authorEntity);
        underTest.save(bookEntity);

        underTest.delete(bookEntity);

        Optional<BookEntity> one = underTest.findById(bookEntity.getIsbn());
        assertThat(one).isEmpty();


    }



}
