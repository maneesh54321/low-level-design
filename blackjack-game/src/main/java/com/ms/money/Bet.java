package com.ms.money;

public class Bet extends Money {

	public Bet(int value) {
		super(value);
	}

	@Override
	public String toString() {
		return "Bet{" + super.toString() + "}";
	}
}
