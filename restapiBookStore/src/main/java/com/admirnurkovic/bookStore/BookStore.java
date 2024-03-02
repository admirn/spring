package com.admirnurkovic.bookStore;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class BookStore {

	public static void main(String[] args) {
		SpringApplication.run(BookStore.class, args);
	}

}
