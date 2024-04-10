package com.ms.json.model;

import java.util.Map;

public class Person {
	private int id;
	private String name;
	private Address address;
	private boolean isEnabled;
	private Map<String, String> ids;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address=" + address +
				", isEnabled=" + isEnabled +
				'}';
	}
}
