import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class CheckInfo {
        private String name;
        private String description;
        private int threshold;
        private String action;
        private int priority;

        public CheckInfo(String name, String description, int threshold, String action, int priority) {
            this.name = name;
            this.description = description;
            this.threshold = threshold;
            this.action = action;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getThreshold() {
            return threshold;
        }

        public String getAction() {
            return action;
        }

        public int getPriority() {
            return priority;
        }
    }
}

