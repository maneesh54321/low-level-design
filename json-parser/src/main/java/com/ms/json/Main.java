package com.ms.json;

import com.ms.json.model.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
//		System.out.println("Hello world!");
		JsonParser parser = new JsonParser();
		try(FileInputStream fileInputStream = new FileInputStream(Main.class.getResource("/person.json").getFile())){
			String json = new String(fileInputStream.readAllBytes());
			Person person = parser.parseJson(json, Person.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}