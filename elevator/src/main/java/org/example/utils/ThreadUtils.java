package org.example.utils;

public class ThreadUtils {

    public static void simulateTimeGap(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
