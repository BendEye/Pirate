package me.systems.bendeye.pirate.manager.fiber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class FiberManager {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        // Create a list of checks
        List<Check> checks = new ArrayList<>();
        checks.add(new Check("Speed Hack", "Detects players moving too fast", 10, "/ban {player}", 1));
        checks.add(new Check("Suspicious Movement", "Detects players suddenly stopping movement", 5, "/warn {player}", 2));

        // Execute custom anti-cheat checks in their own fibers
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

    static class Check {
        private CheckInfo checkInfo;

        public Check(String name, String description, int threshold, String action, int priority) {
            this.checkInfo = new CheckInfo(name, description, threshold, action, priority);
        }

        public void execute() {
            AntiCheatChecker antiCheatChecker = new AntiCheatChecker();
            antiCheatChecker.check(this.checkInfo);
        }
    }
}

