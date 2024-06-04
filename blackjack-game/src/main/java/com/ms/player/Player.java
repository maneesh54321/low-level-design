package com.ms.player;

import com.ms.card.Card;
import com.ms.card.Hand;
import com.ms.turn.Turn;

public abstract class Player {

	private final String name;

	private Hand hand;

	public Player(String name) {
		this.name = name;
	}

	public void takeTurn(Turn turn) {
		System.out.println("Turn starting for player:\n" + this);
		turn.execute();
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand){
		this.hand = hand;
	}

	public void addCard(Card card) {
		this.hand.addCard(card);
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", hand=" + hand +
				'}';
	}

}
