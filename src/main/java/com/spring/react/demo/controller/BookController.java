package com.spring.react.demo.controller;


import com.spring.react.demo.jpa.Book;
import com.spring.react.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("")
    public Flux<Book> getHome() {
        return bookRepository.findAll().log();

    }

    @PostMapping("")
    public Mono<Book> postBook(@RequestBody Book book) {
        Mono<Book> bookMono = bookRepository.save(book).log();
        return bookMono;
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