package org.vendingmachine.v2.state;

import java.util.List;
import java.util.Optional;
import org.vendingmachine.v2.VendingMachine;
import org.vendingmachine.v2.money.Denomination;

public class NoMoneyState implements VendingMachineState {

	private final VendingMachine vendingMachine;

	public NoMoneyState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	@Override
	public void dispenseProduct() {
		System.out.println("No balance available!! Please insert money.");
	}

	@Override
	public void insertMoney(Denomination denomination) {
		Optional<Denomination> money = vendingMachine.getMoneySlot().insertMoney(denomination);
		if(money.isPresent()) {
			System.out.println("Added money.");
			vendingMachine.getMoneyManager().addCurrentBalance(List.of(denomination));
			vendingMachine.setVendingMachineState(new HasMoneyState(vendingMachine));
		}
	}
}
