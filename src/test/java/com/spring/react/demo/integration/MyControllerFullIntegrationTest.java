package com.spring.react.demo.integration;

import com.spring.react.demo.jpa.Book;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyControllerFullIntegrationTest {

    public static final String LOCALHOST_URL = "http://localhost:";

    @LocalServerPort
    private int port;

    private WebTestClient webTestClient;

    @BeforeEach
    public void prepare(){
        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:"+port)
                .build();
    }


    @Test
    void testCreateBook() {
        Book book = Book.builder().id(10000l).title("test Title").author("testAuth").build();;
        // Just to delete the old record with record id 1 if any .
        webTestClient.delete()
                .uri( "/api/books/{bookId}",book.getId())
                .exchange().expectStatus().value(Matchers.anyOf(Matchers.equalTo(HttpStatus.OK.value()),Matchers.equalTo(HttpStatus.BAD_REQUEST.value())));

        webTestClient.post().uri( "/api/books")
                .bodyValue(book)
                .exchange()
                .expectStatus().isCreated();
    }


    @Test
    void testGetBook() {
        Book book = Book.builder().id(100000l).title("test Title").author("testAuth").build();;
        // first crate a book
        webTestClient.post().uri( "/api/books")
                .bodyValue(book)
                .exchange()
                .expectStatus().isCreated();

        webTestClient.get().uri( "/api/books/{bookId}",book.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class);
    }
}
