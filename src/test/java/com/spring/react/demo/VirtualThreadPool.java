package com.spring.react.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadPool {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> System.out.println("Running task in a virtual thread: "
                    + Thread.currentThread().getName()));
        }

        executor.shutdown();
    }
}
