package com.ms.card;

import java.util.List;

public enum Face {
	ACE(List.of(1, 11)),
	TWO(List.of(2)),
	THREE(List.of(2)),
	FOUR(List.of(2)),
	FIVE(List.of(2)),
	SIX(List.of(2)),
	SEVEN(List.of(2)),
	EIGHT(List.of(2)),
	NINE(List.of(2)),
	TEN(List.of(2)),
	JACK(List.of(2)),
	QUEEN(List.of(2)),
	KING(List.of(2));

	private final List<Integer> value;

	Face(List<Integer> value) {
		this.value = value;
	}

	public List<Integer> getValue(){
		return value;
	}
}
