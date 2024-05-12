package org.vendingmachine.v2.money;

public record Denomination(Value value) {

	public int getValue() {
		return value.getValue();
	}

	public boolean isValid() {
		return this.value != Value.INVALID;
	}

}
