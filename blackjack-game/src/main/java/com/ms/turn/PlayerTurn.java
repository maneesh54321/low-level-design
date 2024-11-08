package com.ms.turn;

import com.ms.Game;
import com.ms.money.Bet;
import com.ms.money.Money;
import com.ms.player.CasinoPlayer;
import java.util.Scanner;

public class PlayerTurn extends Turn<CasinoPlayer> {

	public PlayerTurn(CasinoPlayer player, Game game) {
		super(player, game);
	}

	@Override
	public void play() {
		if (getPlayer().getHand().isBusted()) {
			handleBust();
		} else if (getPlayer().getHand().isBlackjack()) {
			handleBlackJackWinner();
		} else {
			Scanner sc = Game.SCANNER;
			System.out.println("Player: " + getPlayer());
			System.out.print("""
					What do you want to do?
					1. HIT
					2. STAND
					""");
			var action = Integer.parseInt(sc.nextLine());
			switch (action) {
				case 1 -> getGame().hit(getPlayer());
				case 2 -> {
					getGame().stand(getPlayer());
					handleTurnOver();
				}
				default -> System.out.println("Invalid choice!!");
			}
		}
	}

	private void handleTurnOver() {
		over();
	}

	private void handleBust() {
		getGame().declareLoser(getPlayer());
		over();
	}

	private void handleBlackJackWinner() {
		Bet bet = getGame().getBet(getPlayer());
		getGame().declareWinner(getPlayer(), new Money(bet.getValue() + bet.getValue() / 2));
		over();
	}
}
