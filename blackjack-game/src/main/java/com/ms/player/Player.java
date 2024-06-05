package com.ms.player;

import com.ms.card.Card;
import com.ms.card.Hand;
import com.ms.turn.Turn;
import java.util.Objects;

public abstract class Player {

	private final String name;

	private Hand hand;

	public Player(String name) {
		this.name = name;
	}

	public void takeTurn(Turn<?> turn) {
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Player player = (Player) o;
		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", hand=" + hand +
				'}';
	}

}
