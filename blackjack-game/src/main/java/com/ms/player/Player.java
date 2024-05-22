package com.ms.player;

import com.ms.Game;
import com.ms.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

	private final List<Card> cards;

	private final Game game;

	public Player(Game game) {
		this.game = game;
		this.cards = new ArrayList<>();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void takeTurn() {

	}

	public void addCards(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public void addCard(Card card) {
		cards.add(card);
	}
}
