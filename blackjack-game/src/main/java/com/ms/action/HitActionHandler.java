package com.ms.action;

import com.ms.Game;
import com.ms.card.Card;
import com.ms.player.Player;

public class HitActionHandler implements ActionHandler {

	private final Game game;

	public HitActionHandler(Game game) {
		this.game = game;
	}

	@Override
	public void handle(Player player) {
		Card card = game.getShoe().takeCard().show();
		System.out.println("Card drawn: \n" + card);
		player.addCard(card);
	}
}
