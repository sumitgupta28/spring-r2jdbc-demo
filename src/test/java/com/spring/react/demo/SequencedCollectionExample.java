package com.spring.react.demo;

import java.util.ArrayList;
import java.util.SequencedCollection;

public class SequencedCollectionExample {
    public static void main(String[] args) {
        SequencedCollection<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        System.out.println("First element: " + names.getFirst()); // Output: Alice
        System.out.println("Last element: " + names.getLast());   // Output: Charlie

        names.addFirst("David");
        names.addLast("Eve");



        System.out.println("Collection after additions: " + names); // Output: [David, Alice, Bob, Charlie, Eve]

        SequencedCollection<String> reversedNames = names.reversed();
        System.out.println("Reversed view: " + reversedNames); // Output: [Eve, Charlie, Bob, Alice, David]
    }
}