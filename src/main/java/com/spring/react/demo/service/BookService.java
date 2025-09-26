package com.spring.react.demo.service;

import com.spring.react.demo.exception.BookServiceException;
import com.spring.react.demo.jpa.Book;
import com.spring.react.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Mono<Book> createBook(Book book) {
        return bookRepository.findById(book.getId()).flatMap(existingBook ->
                {
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setTitle(book.getTitle());
                    return bookRepository.save(existingBook);
                })
                .switchIfEmpty(Mono.defer(() -> bookRepository.save(book)));
    }

    public Flux<Book> findAllBooks() {
        BookServiceException bookServiceException = new BookServiceException("No Books Found", "findAllBooks", "10001");
        return bookRepository.findAll()
                .switchIfEmpty(Mono.error(bookServiceException)); // If not found, throw a custom exception;
    }

    public Mono<Book> updateBook(Book book) {
        Long bookId = book.getId();
        BookServiceException bookServiceException = new BookServiceException("Book not found with id: " + bookId, "updateBook", "10001");
        return bookRepository.findById(bookId) // Find the entity by ID
                .flatMap(existingBook ->
                {
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setTitle(book.getTitle());
                    return bookRepository.save(existingBook);
                })
                .switchIfEmpty(Mono.error(bookServiceException)); // If not found, throw a custom exception
    }


    public Mono<Book> getBookById(Long bookId) {
        BookServiceException bookServiceException = new BookServiceException("Book not found with id: " + bookId, "getBookById", "10001");
        return bookRepository.findById(bookId) // Find the entity by ID
                .switchIfEmpty(Mono.error(bookServiceException)); // If not found, throw a custom exception
    }


    public Mono<Long> deleteBookById(Long bookId) {
        BookServiceException bookServiceException = new BookServiceException("Book not found with id: " + bookId, "deleteBook", "10001");
        return bookRepository.findById(bookId) // Find the entity by ID
                .flatMap(product -> bookRepository.deleteById(bookId)).thenReturn(bookId) // If found, delete it
                .switchIfEmpty(Mono.error(bookServiceException)); // If not found, throw a custom exception
    }

}
