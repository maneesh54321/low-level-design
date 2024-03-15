package org.ms.elevator.utils;

public class ThreadUtils {
	public static void simulateDelay(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
