package com.spring.react.demo;

public class VirtualThreadsTest {

    public static void main(String[] args) {
        Thread virtualThread = Thread.startVirtualThread(() -> System.out.println("Running task in a virtual thread: "
                + Thread.currentThread().getName()));

        // Waiting for virtual threads to complete
        try {
            virtualThread.join();
        } catch (InterruptedException e) {

        }
    }
}
