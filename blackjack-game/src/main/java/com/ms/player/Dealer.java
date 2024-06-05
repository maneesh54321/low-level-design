package com.ms.player;

import com.ms.Game;
import com.ms.card.Hand;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dealer extends Player {

	private final Game game;

	public Dealer(String name, Game game) {
		super(name);
		this.game = game;
	}

	public void dealHands() {
		System.out.println("Dealing cards...");
		dealCards();
	}

	private void dealCards() {
		var shoe = game.getShoe();
		game.getTable().players().stream()
				.peek(player -> player.setHand(
						new Hand(Stream.of(shoe.takeCard().show(), shoe.takeCard().show()).collect(Collectors.toList()))))
				.forEach(System.out::println);
		setHand(new Hand(Stream.of(shoe.takeCard(), shoe.takeCard().show()).collect(Collectors.toList())));
		System.out.println(this);
	}
}
