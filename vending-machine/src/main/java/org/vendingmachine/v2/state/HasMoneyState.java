package org.vendingmachine.v2.state;

import org.vendingmachine.v2.VendingMachine;
import org.vendingmachine.v2.money.Denomination;

public class HasMoneyState implements VendingMachineState {

	private final VendingMachine vendingMachine;

	public HasMoneyState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	@Override
	public void dispenseProduct() {

	}

	@Override
	public void insertMoney(Denomination denomination) {

	}
}
