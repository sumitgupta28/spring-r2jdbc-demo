package com.spring.react.demo;

import reactor.core.publisher.Flux;

public class DoOnErrorExample {

    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5)
                .concatWith(Flux.error(new RuntimeException("Oops! An error occurred.")))
                .map(number -> 10 / (number - 3)) // This will cause an ArithmeticException
                .doOnError(throwable -> System.err.println("Error occurred: " + throwable.getMessage()))
                .onErrorReturn(-1); // Provide a fallback value in case of an error

        numbers.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Subscriber error: " + error.getMessage())
        );
    }
}
