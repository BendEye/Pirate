package me.systems.bendeye.pirate.manager.fiber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class FiberManager {
    private List<Check> checks;

    public FiberManager() {
        checks = new ArrayList<>();
        // Example checks (add more as needed)
        checks.add(new ExampleCheck());
    }

    public void init() {
        ForkJoinPool pool = new ForkJoinPool();
        for (Check check : checks) {
            pool.execute(() -> check.execute());
        }
        pool.shutdown();
        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
