package com.spring.react.demo;

public class PatternMatching {

    public static void processObject(Object obj) {
        // Traditional approach (pre-Java 17)
        if (obj instanceof String) {
            String s = (String) obj; // Explicit cast required
            System.out.println("Traditional: This is a String: " + s.toUpperCase());
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj; // Explicit cast required
            System.out.println("Traditional: This is an Integer: " + (i * 2));
        }

        // Pattern matching for instanceof (Java 17+)
        if (obj instanceof String str) { // 'str' is the pattern variable
            System.out.println("Pattern Matching: This is a String: " + str.toLowerCase());
        } else if (obj instanceof Integer num) { // 'num' is the pattern variable
            System.out.println("Pattern Matching: This is an Integer: " + (num + 10));
        }
    }

    public static void main(String[] args) {
        processObject("Hello Java 17");
        processObject(123);
        processObject(45.67); // Neither String nor Integer
    }
}
