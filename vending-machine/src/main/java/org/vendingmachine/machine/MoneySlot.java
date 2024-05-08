package org.vendingmachine.machine;

public class MoneySlot {
	private VendingMachine vendingMachine;

	public MoneySlot(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	public void insertMoney(int amount){
		this.vendingMachine.addBalance(amount);
	}
}
