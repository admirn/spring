package com.admirnurkovic.book;

import com.admirnurkovic.book.domain.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {

    @Test
    public void testThatJsonTransformToJavaObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = Book.builder()
                .isbn("733-7333-733")
                .title("Simple Book")
                .author("Admir Nurkovic")
                .yearPublished("1989")
                .build();

        String result = objectMapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"isbn\":\"733-7333-733\",\"title\":\"Simple Book\",\"author\":\"Admir Nurkovic\",\"year\":\"1989\"}");


    }

    @Test
    public void testThatObjectJavaTransformsToJson() throws JsonProcessingException {
        Book book = Book.builder()
                .isbn("733-7333-733")
                .title("Simple Book")
                .author("Admir Nurkovic")
                .yearPublished("1989")
                .build();

        String json = "{\"isbn\":\"733-7333-733\",\"title\":\"Simple Book\",\"author\":\"Admir Nurkovic\",\"year\":\"1989\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        Book result = objectMapper.readValue(json, Book.class);
        assertThat(result).isEqualTo(book);

    }
}
