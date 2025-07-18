package com.spring.react.demo;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureWithVirtualThread {

    public static void main(String[] args) {

        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> "Virtual Thread");
        completableFuture.thenApplyAsync(String::toUpperCase);
        CompletableFuture<Void> future = completableFuture
                .thenAcceptAsync(uppercaseResult -> {
                    System.out.println("Uppercase result: " + uppercaseResult +
                            " in thread: " + Thread.currentThread().getName());
                });

        future.join();
    }
}
