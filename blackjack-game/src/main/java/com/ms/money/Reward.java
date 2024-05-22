package com.ms.money;

public class Reward extends Money {

	private final Bet bet;

	public Reward(Bet bet) {
		this.bet = bet;
	}

	public Bet getBet() {
		return bet;
	}
}
