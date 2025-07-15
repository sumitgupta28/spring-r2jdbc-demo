package com.spring.react.demo.repository;

import com.spring.react.demo.jpa.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
}