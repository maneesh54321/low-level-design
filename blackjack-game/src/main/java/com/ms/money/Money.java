package com.ms.money;

public class Money {

	private final int value;

	public Money(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Money{" +
				"value=" + value +
				'}';
	}
}
