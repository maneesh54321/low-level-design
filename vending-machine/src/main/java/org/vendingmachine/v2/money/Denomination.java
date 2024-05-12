package org.vendingmachine.v2.money;

public class Denomination {

	private final Value value;

	public Denomination(Value value) {
		this.value = value;
	}

	public int getValue() {
		return value.getValue();
	}

	public boolean isValid() {
		return this.value != Value.INVALID;
	}
}
