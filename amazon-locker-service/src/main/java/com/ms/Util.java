package com.ms;

import java.util.Random;

public class Util {
	public static final Random RANDOM = new Random();

	public static String generateRandomCode(){
		return "%06d".formatted(RANDOM.nextInt(1000_000));
	}
}
