package com.spring.react.demo.controller;


import com.spring.react.demo.exception.BookServiceException;
import com.spring.react.demo.jpa.Book;
import com.spring.react.demo.model.Response;
import com.spring.react.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;



    @ExceptionHandler(BookServiceException.class)
    public ResponseEntity<Response> handleBookServiceException(BookServiceException ex) {
        Response errorResponse = Response.builder().message(ex.getMessage())
                .errorCode(ex.getErrorCode()).apiName(ex.getApiName()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @RequestMapping(path = "/{bookId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Book>> createNewBook(@RequestBody Book book) {
        log.debug(" Book  to Be created {} ", book);
        return bookService.createBook(book)
                .map(book1 -> ResponseEntity.status(HttpStatus.CREATED).body(book1));
    }

    @PutMapping(path = "",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<Response> deleteBook(@PathVariable("bookId") Long bookId) {
        Mono<Long> longMono =bookService.deleteBookById(bookId) ;
        return new ResponseEntity<>(Response.builder().message("Book Id ["+bookId+"] delete Successfully").apiName("deleteBook").build(), HttpStatus.OK);
    }
}