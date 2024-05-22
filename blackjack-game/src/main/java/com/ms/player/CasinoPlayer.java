package com.ms.player;

import com.ms.money.Bet;
import com.ms.Game;
import com.ms.money.Reward;

public class CasinoPlayer extends Player implements Gambler {
	public CasinoPlayer(Game game) {
		super(game);
	}

	@Override
	public void placeBet(Bet bet) {

	}

	@Override
	public void win(Reward reward) {

	}

	@Override
	public void lose(Bet bet) {

	}
}
