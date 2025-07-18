package com.spring.react.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveChainingExmaple {

    public static void main(String[] args) {

        // Chaining map, filter, and reduce

        Flux<Integer> numbers = Flux.range(1, 5);

        Mono<Integer> sumOfEvenSquaredNumbers = numbers
                .filter(number -> number % 2 == 0) // Filter even numbers
                .map(evenNumber -> {
                    System.out.println(evenNumber); return evenNumber * evenNumber; }) // Square the even numbers
                .reduce(0, Integer::sum); // Calculate sum
        sumOfEvenSquaredNumbers.subscribe(result -> System.out.println("Sum: " + result));

        // Using zip and map together

        Flux<Integer> numberValues = Flux.range(1, 6);
        Flux<String> words = Flux.just("one", "two", "three", "four", "five");
        Flux<String> combined = Flux.zip(numberValues, words, (number, word) -> number + ": " + word);
        combined.subscribe(System.out::println);

        // Combining flatMap and filter

        Flux<String> letters = Flux.just("A", "B", "C");
        Flux<String> transformedLetters = letters
                .flatMap(letter -> Flux.just(letter, letter.toLowerCase())) // Transform each letter into upper and lower case
                .filter(transformedLetter -> !transformedLetter.equals("a")); // Filter out lowercase 'a'
        transformedLetters.subscribe(System.out::println);
    }
}
