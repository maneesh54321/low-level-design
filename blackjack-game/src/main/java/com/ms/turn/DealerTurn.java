package com.ms.turn;

import com.ms.Game;
import com.ms.action.Action;
import com.ms.money.Bet;
import com.ms.money.Reward;
import com.ms.player.Player;

public class DealerTurn extends Turn {

	public DealerTurn(Player player, Game game) {
		super(player, game);
		player.getHand().showAllCards();
	}

	@Override
	public void play() {
		if (getPlayer().getHand().isBlackjack()) {
			handleBlackjack();
			over();
		} else if (getPlayer().getHand().isBusted()) {
			handleBust();
			over();
		} else if (isDealerTurnOver()) {
			getGame().play(getPlayer(), Action.STAND);
			handleGameCompletion();
			over();
		} else {
			getGame().play(getPlayer(), Action.HIT);
		}
	}

	private void handleGameCompletion() {
		// Dealer's hand is checked, if blackjack, all remaining players lose,
		// if dealer is busted, all remaining players lose,
		if (getPlayer().getHand().isBlackjack()) {
			handleBlackjack();
		} else if (getPlayer().getHand().isBusted()) {
			handleBust();
		} else {
			getGame().getTable().players().forEach(casinoPlayer -> {
				// else, all players having
				if (casinoPlayer.getHand().getTotalValue() > getPlayer().getHand().getTotalValue()) {
					// 1. more value than dealer win
					Bet bet = getGame().getBet(casinoPlayer);
					getGame().declareWinner(casinoPlayer,
							new Reward(bet, bet.getValue() + bet.getValue() / 2));
				} else if (casinoPlayer.getHand().getTotalValue() < getPlayer().getHand().getTotalValue()) {
					// 2. less value than dealer lose
					getGame().declareLoser(casinoPlayer);
				} else {
					// 3. equal value as dealer draw
					getGame().declareDraw(casinoPlayer);
				}
			});
		}
	}

	private void handleBust() {
		// all remaining player(s) win.
		getGame().getTable().players().forEach(casinoPlayer -> {
			Bet bet = getGame().getBet(casinoPlayer);
			getGame().declareWinner(casinoPlayer, new Reward(bet, bet.getValue() + bet.getValue()/2));
		});
	}

	private void handleBlackjack() {
		// all remaining player(s) lose
		getGame().getTable().players().forEach(casinoPlayer -> getGame().declareLoser(casinoPlayer));
	}

	private boolean isDealerTurnOver() {
		return getPlayer().getHand().isSoftSeventeen()
				|| getPlayer().getHand().isHardSeventeen();
	}
}
