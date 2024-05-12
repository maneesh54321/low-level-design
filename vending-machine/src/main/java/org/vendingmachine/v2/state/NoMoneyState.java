package org.vendingmachine.v2.state;

import org.vendingmachine.v2.VendingMachine;
import org.vendingmachine.v2.money.Denomination;

public class NoMoneyState implements VendingMachineState {

	private final VendingMachine vendingMachine;

	public NoMoneyState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	@Override
	public void dispenseProduct() {

	}

	@Override
	public void insertMoney(Denomination denomination) {

	}
}
