package org.ms.utils;

import java.util.UUID;

public class UniqueIdUtil {
	public static String generateUniqueId(){
		return UUID.randomUUID().toString();
	}
}
