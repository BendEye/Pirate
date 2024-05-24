import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ExampleCheck extends Check {
        public ExampleCheck() {
            super("Example Check", "An example custom check", 3, "/kick {player}", 3);
        }
    }
}
