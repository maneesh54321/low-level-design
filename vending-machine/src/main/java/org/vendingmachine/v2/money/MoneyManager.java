package org.vendingmachine.v2.money;

import java.util.ArrayList;
import java.util.List;

public class MoneyManager {

	private final MoneyDispenser moneyDispenser;

	private final Balance currentBalance;

	private final Balance totalBalance;

	public MoneyManager(MoneyDispenser moneyDispenser, Balance totalBalance) {
		this.moneyDispenser = moneyDispenser;
		this.currentBalance = new Balance(0, new ArrayList<>());
		this.totalBalance = totalBalance;
	}

	public int getCurrentBalance() {
		return currentBalance.getAmount();
	}

	public void releaseCurrentBalance() {
		this.moneyDispenser.dispense(currentBalance.getDenominationList());
		currentBalance.resetToZero();
	}

	public boolean hasChange(int amount) {
		// TODO implement this.
		return true;
	}

	public void earn(int amount) {
		var currentBalance = this.currentBalance.getAmount();
		this.totalBalance.add(this.currentBalance.getDenominationList());
		var amountLeftAfterEarning = currentBalance - amount;
		var change = calculateChange(amountLeftAfterEarning, this.totalBalance.getDenominationList());
		this.totalBalance.take(change);
	}

	private List<Denomination> calculateChange(int amount, List<Denomination> denominationList) {
		// TODO implement this
		return List.of();
	}

	public void addCurrentBalance(List<Denomination> denominations) {
		this.currentBalance.add(denominations);
	}
}
