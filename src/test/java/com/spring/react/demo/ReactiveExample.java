package com.spring.react.demo;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Executors;

public class ReactiveExample {

    public static void main(String[] args) {

        // 1. Create a Publisher (SubmissionPublisher)
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>(Executors.newFixedThreadPool(2), Flow.defaultBufferSize());

        // 2. Create a Subscriber
        MySubscriber subscriber = new MySubscriber();

        // 3. Subscribe the Subscriber to the Publisher
        publisher.subscribe(subscriber);

        // 4. Publish items
        publisher.submit("Hello");
        publisher.submit("Reactive");
        publisher.submit("Streams");

        // Introduce an error (simulated)
        // Note: In a real-world scenario, errors would typically arise from operations within the Publisher.
        try {
            throw new RuntimeException("Something went wrong!");
        } catch (RuntimeException e) {
            publisher.closeExceptionally(e); // Propagate the error to the Subscriber
        }


        // Wait for a short period to allow for asynchronous processing
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the publisher
        publisher.close();

    }

    // Custom Subscriber implementation
    static class MySubscriber implements Flow.Subscriber<String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            System.out.println("Subscriber: Subscribed!");
            subscription.request(Long.MAX_VALUE); // Request all available items, {Link: Oracle Help Center says, effectively unbounded https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/Flow.html}
        }

        @Override
        public void onNext(String item) {
            System.out.println("Subscriber: Received item: " + item);
            // Process the item
        }

        @Override
        public void onError(Throwable throwable) {
            System.err.println("Subscriber: Error occurred: " + throwable.getMessage());
            // Handle the error (e.g., log it, retry, etc.)
            // The subscription is terminated after onError().
        }

        @Override
        public void onComplete() {
            System.out.println("Subscriber: Subscription completed!");
            // Perform any final cleanup or actions
            // The subscription is terminated after onComplete().
        }
    }
}
