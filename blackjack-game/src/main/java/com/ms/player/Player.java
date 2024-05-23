package com.ms.player;

import com.ms.Game;
import com.ms.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

	private final String name;

	private final List<Card> cards;

	private final Game game;

	public Player(String name, Game game) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", cards=" + cards +
				'}';
	}
}
