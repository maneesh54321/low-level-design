package org.vendingmachine.v2.state;

import java.util.List;
import java.util.Optional;
import org.vendingmachine.v2.VendingMachine;
import org.vendingmachine.v2.money.Denomination;

public class HasMoneyState implements VendingMachineState {

	private final VendingMachine vendingMachine;

	public HasMoneyState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	@Override
	public void dispenseProduct() {
		// ask for selection of product
		vendingMachine.getDisplay().display("Enter rack id of product.");
		int rackId = vendingMachine.getSelectionPad().getSelectionInput();
		if (!vendingMachine.getInventory().hasProduct(rackId)) {
			System.out.println("Product not available!!");
			vendingMachine.getMoneyManager().releaseCurrentBalance();
			return;
		}
		// check if there is enough money for the selected product and
		// system has change if there is extra balance
		int productPrice = vendingMachine.getInventory().getPrice(rackId);
		int currentBalance = vendingMachine.getMoneyManager()
				.getCurrentBalance();
		if (productPrice > currentBalance) {
			System.out.println("Product price is more than the balance!!");
			vendingMachine.getMoneyManager().releaseCurrentBalance();
			return;
		}
		if (productPrice - currentBalance > 0 && !vendingMachine.getMoneyManager()
				.hasChange(productPrice - currentBalance)) {
			System.out.println("Don't have change!!");
			vendingMachine.getMoneyManager().releaseCurrentBalance();
			return;
		}
		// dispense product
		vendingMachine.getInventory().dispenseProduct(rackId);
		// earn money
		vendingMachine.getMoneyManager().earn(productPrice);
		// dispense change
		if(productPrice - currentBalance > 0) {
			vendingMachine.getMoneyManager().releaseCurrentBalance();
		}
		// change state to no money
		vendingMachine.setVendingMachineState(new NoMoneyState(vendingMachine));
	}

	@Override
	public void insertMoney(Denomination denomination) {
		Optional<Denomination> money = vendingMachine.getMoneySlot().insertMoney(denomination);
		if (money.isPresent()) {
			vendingMachine.getMoneyManager().addCurrentBalance(List.of(denomination));
		}
	}
}
