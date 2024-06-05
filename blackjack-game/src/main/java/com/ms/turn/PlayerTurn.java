package com.ms.turn;

import com.ms.Game;
import com.ms.action.Action;
import com.ms.money.Bet;
import com.ms.money.Money;
import com.ms.player.Gambler;
import com.ms.player.Player;
import java.util.Scanner;

public class PlayerTurn extends Turn {

	public PlayerTurn(Player player, Game game) {
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
			if (action == 1) {
				getGame().play(getPlayer(), Action.HIT);
			} else if (action == 2) {
				getGame().play(getPlayer(), Action.STAND);
				handleTurnOver();
			} else {
				System.out.println("Invalid choice!!");
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
		Bet bet = getGame().getBet((Gambler) getPlayer());
		getGame().declareWinner(getPlayer(), new Money(bet.getValue() + bet.getValue() / 2));
		over();
	}
}
