package com.ms.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shoe {

	private List<Card> deck;

	public Shoe() {
		deck = new ArrayList<>();
		// for each Suit
		Arrays.stream(Suit.values()).forEach(suit -> {
			// add ace, king, queen, jack
			deck.add(new Card(Face.ACE, suit));
			deck.add(new Card(Face.KING, suit));
			deck.add(new Card(Face.QUEEN, suit));
			deck.add(new Card(Face.JACK, suit));
			// add number cards
			deck.add(new Card(Face.TWO, suit));
			deck.add(new Card(Face.THREE, suit));
			deck.add(new Card(Face.FOUR, suit));
			deck.add(new Card(Face.FIVE, suit));
			deck.add(new Card(Face.SIX, suit));
			deck.add(new Card(Face.SEVEN, suit));
			deck.add(new Card(Face.EIGHT, suit));
			deck.add(new Card(Face.NINE, suit));
			deck.add(new Card(Face.TEN, suit));
		});
	}

	public Shoe(List<Card> deck) {
		this.deck = deck;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}

	public Card takeCard(){
		return deck.removeLast();
	}
}
