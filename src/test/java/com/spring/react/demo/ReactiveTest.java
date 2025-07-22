package com.spring.react.demo;

import reactor.core.publisher.Flux;

public class ReactiveTest {

    public static void main(String[] args) {

        Flux<Integer> originalFlux = Flux.just(1, 2, 3);
        Flux<String> mappedFlux = originalFlux.map(number -> "Number: " + number);

        // Subscribe to the Flux and print each element
        mappedFlux.subscribe(
                System.out::println, // Consumer for each element
                error -> System.err.println("Error: " + error), // Consumer for errors (optional)
                () -> System.out.println("Flux completed!") // Runnable for completion (optional)
        );

        Flux<Integer> numbers = Flux.range(1, 10);
        Flux<Integer> evenNumbers = numbers.filter(number -> number % 2 == 0);
        evenNumbers.subscribe(System.out :: println ,
                error -> System.err.println("Error: " + error), // Consumer for errors (optional)
                () -> System.out.println("numbers.filter Completed"));

        Flux<String> letterFlux = numbers.filter(integer -> integer%2==0).flatMap(number -> Flux.just("A", "B","C").map(letter -> number + letter));
        letterFlux.subscribe(System.out :: println ,
                error -> System.err.println("Error: " + error), // Consumer for errors (optional)
                () -> System.out.println("Flux.just(\"A\", \"B\",\"C\").map Completed"));


        Flux<String> letters = Flux.just("A", "B", "C");
        Flux<String> combined = Flux.zip(numbers, letters, (number, letter) -> number + letter);
        combined.subscribe(System.out :: println ,
                error -> System.err.println("Error: " + error), // Consumer for errors (optional)
                () -> System.out.println("Flux.zip Completed"));


        Flux<Integer> flux1 = Flux.just(1, 3, 5);
        Flux<Integer> flux2 = Flux.just(4, 7, 8);
        Flux<Integer> mergedFlux = Flux.merge(flux1, flux2); // interleaved
        Flux<Integer> concatenatedFlux = Flux.concat(flux1, flux2); // in order

        mergedFlux.subscribe(System.out :: println ,
                error -> System.err.println("Error: " + error), // Consumer for errors (optional)
                () -> System.out.println("Completed mergedFlux " ));

        concatenatedFlux.subscribe(System.out :: println ,
                error -> System.err.println("Error: " + error), // Consumer for errors (optional)
                () -> System.out.println("Completed concat "));

    }
}
