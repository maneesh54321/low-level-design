package com.ms.money;

public class Reward extends Money {

	private final Bet bet;

	public Reward(Bet bet, int value) {
		super(value);
		this.bet = bet;
	}

	public Bet getBet() {
		return bet;
	}

	@Override
	public String toString() {
		return "Reward{" +
				"bet=" + bet + ", " + super.toString() +
				'}';
	}
}
