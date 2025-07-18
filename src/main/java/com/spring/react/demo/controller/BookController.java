package com.spring.react.demo.controller;


import com.spring.react.demo.jpa.Book;
import com.spring.react.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("")
    public Flux<ResponseEntity<Book>> getHome() {
        return bookRepository.findAll().map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Mono<ResponseEntity<Book>> postBook(@RequestBody Book book) {
        return bookRepository.save(book)
                .map(book1 -> ResponseEntity.status(HttpStatus.CREATED).body(book1));
    }

    @PutMapping("")
    public Mono<Book> updateBook(@RequestBody Book book) {
        return bookRepository.save(book);

    }

    @DeleteMapping("")
    public boolean deleteBook(@RequestBody Book book) {
        try {
            bookRepository.deleteById(book.getId()).block(); // Note this!
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}