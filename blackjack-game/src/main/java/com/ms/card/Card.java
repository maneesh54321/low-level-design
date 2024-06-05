package com.ms.card;

import java.util.Objects;

public final class Card {

	private final Face face;
	private final Suit suit;
	private boolean hidden = true;

	public Card(Face face, Suit suit) {
		this.face = face;
		this.suit = suit;
	}

	public Card show() {
		this.hidden = false;
		return this;
	}

	public Face face() {
		return face;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		var that = (Card) obj;
		return Objects.equals(this.face, that.face) &&
				Objects.equals(this.suit, that.suit) &&
				Objects.equals(this.hidden, that.hidden);
	}

	@Override
	public int hashCode() {
		return Objects.hash(face, suit, hidden);
	}

	@Override
	public String toString() {
		return "Card" +
				(hidden ? "" : "[face=" + face + ", " +
				"suit=" + suit + "]");
	}

}
