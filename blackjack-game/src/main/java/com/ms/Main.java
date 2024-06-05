package com.ms;

import com.ms.money.Bet;
import com.ms.player.CasinoPlayer;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		CasinoPlayer ram = new CasinoPlayer("Ram", game);
		ram.placeBet(new Bet(30));
		CasinoPlayer mohan = new CasinoPlayer("Mohan", game);
		mohan.placeBet(new Bet(40));
		CasinoPlayer sita = new CasinoPlayer("Sita", game);
		sita.placeBet(new Bet(80));
		game.start();
	}
}