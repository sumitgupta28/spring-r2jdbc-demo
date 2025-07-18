package com.spring.react.demo;

public class VirtualThreadDemo {

    public static void main(String[] args) throws InterruptedException {


        // Create a virtual thread using the builder pattern
        Thread.Builder builder = Thread.ofVirtual().name("MyVirtualThread");
        Runnable task = () -> System.out.println("Hello from " + Thread.currentThread().getName());
        Thread virtualThread = builder.start(task);

        // Wait for the virtual thread to complete
        virtualThread.join();
        virtualThread().join();
        System.out.println("Main thread finished.");


    }

    public static Thread virtualThread() throws InterruptedException {

        return Thread.startVirtualThread(() ->
                        System.out.println("Running Task in Virtual Thread :: "+Thread.currentThread().getName())
                );


    }
}
