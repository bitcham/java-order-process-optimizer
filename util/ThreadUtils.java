package util;

import static util.MyLogger.log;

public abstract class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("Interrupt occurred, " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
