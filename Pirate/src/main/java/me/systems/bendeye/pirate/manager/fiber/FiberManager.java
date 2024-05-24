package me.systems.bendeye.pirate.manager.fiber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FiberManager {
    private ExecutorService executorService;
    private SubmissionPublisher<Fiber> publisher;

    public FiberManager() {
        this.executorService = Executors.newFixedThreadPool(5); // You can adjust the pool size as needed
        this.publisher = new SubmissionPublisher<>(executorService, Flow.defaultBufferSize());
    }

    public void submitFiber(Fiber fiber) {
        publisher.submit(fiber);
    }

    public void start() {
        publisher.subscribe(new FiberSubscriber());
    }

    public void stop() {
        publisher.close();
        executorService.shutdown();
    }

    private static class FiberSubscriber implements Flow.Subscriber<Fiber> {
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1); // Request the first fiber
        }

        @Override
        public void onNext(Fiber item) {
            item.start();
            subscription.request(1); // Request the next fiber
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("All fibers have been processed.");
        }
    }

    public static void main(String[] args) {
        FiberManager fiberManager = new FiberManager();

        // Submit some fibers
        fiberManager.submitFiber(new Fiber("Fiber 1"));
        fiberManager.submitFiber(new Fiber("Fiber 2"));
        fiberManager.submitFiber(new Fiber("Fiber 3"));
        fiberManager.submitFiber(new Fiber("Fiber 4"));
        fiberManager.submitFiber(new Fiber("Fiber 5"));

        // Start processing fibers
        fiberManager.start();

        // Do some other work...

        // Stop the manager when done
        fiberManager.stop();
    }
}

class Fiber implements Runnable {
    private String name;

    public Fiber(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Executing fiber: " + name);
        // Simulate some work
        try {
            Thread.sleep(1000); // Simulate work for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fiber completed: " + name);
    }

    public void start() {
        Thread thread = Thread.startVirtualThread(this);
        // You can also use: Thread thread = Thread.ofVirtual().start(this);
    }
}