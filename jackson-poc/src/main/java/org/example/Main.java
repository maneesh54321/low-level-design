package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");

		JsonFactory jsonFactory = JsonFactory.builder().build();

		try {
			JsonParser parser = jsonFactory.createParser(Main.class.getResourceAsStream("/large-file.json"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}