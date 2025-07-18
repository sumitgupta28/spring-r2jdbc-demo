package com.spring.react.demo;

import java.util.List;
import java.util.stream.IntStream;

public class PrimeGeneratorTest {

    public static void main(String[] args) {

        ResultClass resultClass = new ResultClass();
        Runnable runnable = new PrimeGenerator(resultClass);

        // Since Java 16
        List<Integer> list1 = IntStream.rangeClosed(1, 20)
                .boxed().toList();

        list1.forEach(integer ->
                {
                    Thread t = new Thread(runnable);
                    t.start();
                }
        );

        //System.out.println(resultClass.getPrimes());
    }
}
