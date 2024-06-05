package com.ms.player;

import com.ms.money.Bet;
import com.ms.Game;
import com.ms.money.Reward;

public class CasinoPlayer extends Player implements Gambler {

	private final Game game;

	public CasinoPlayer(String name, Game game) {
		super(name);
		this.game = game;
	}

	@Override
	public void placeBet(Bet bet) {
		game.placeBet(this, bet);
	}

	@Override
	public void win(Reward reward) {
		System.out.println("Won!!! " + reward);
	}

	@Override
	public void lose(Bet bet) {
		System.out.println("Lost!! " + bet);
	}

	@Override
	public void draw(Bet bet) {
		System.out.println("Draw!! " + bet);
	}
}
