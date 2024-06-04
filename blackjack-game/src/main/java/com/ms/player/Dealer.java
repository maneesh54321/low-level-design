package com.ms.player;

import com.ms.Game;
import com.ms.card.Hand;
import java.util.List;

public class Dealer extends Player {

	protected final Game game;

	public Dealer(String name, Game game) {
		super(name);
		this.game = game;
	}

	public void dealHands(){
		System.out.println("Dealing cards...");
		dealCards();
	}

	private void dealCards() {
		var shoe = game.getShoe();
		game.getPlayers().stream().peek(player -> player.setHand(new Hand(List.of(shoe.takeCard().show(), shoe.takeCard().show()))))
				.forEach(System.out::println);
		setHand(new Hand(List.of(shoe.takeCard(), shoe.takeCard().show())));
	}
}
