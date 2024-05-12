package org.vendingmachine.v2;

import org.vendingmachine.v2.money.Denomination;
import org.vendingmachine.v2.money.MoneyManager;
import org.vendingmachine.v2.money.MoneySlot;
import org.vendingmachine.v2.product.ProductInventory;
import org.vendingmachine.v2.state.NoMoneyState;
import org.vendingmachine.v2.state.VendingMachineState;

public class VendingMachine {

	private final ProductInventory inventory;

	private final MoneyManager moneyManager;

	private final SelectionPad selectionPad;

	private final Display display;

	private final MoneySlot moneySlot;

	private VendingMachineState vendingMachineState;

	public VendingMachine(ProductInventory inventory, MoneyManager moneyManager,
			SelectionPad selectionPad, Display display, MoneySlot moneySlot) {
		this.inventory = inventory;
		this.moneyManager = moneyManager;
		this.selectionPad = selectionPad;
		this.display = display;
		this.moneySlot = moneySlot;
		vendingMachineState = new NoMoneyState(this);
	}

	public void dispenseProduct(){
		this.vendingMachineState.dispenseProduct();
	}

	public void insertMoney(Denomination denomination) {
		this.vendingMachineState.insertMoney(denomination);
	}

	public ProductInventory getInventory() {
		return inventory;
	}

	public MoneyManager getMoneyManager() {
		return moneyManager;
	}

	public SelectionPad getSelectionPad() {
		return selectionPad;
	}

	public Display getDisplay() {
		return display;
	}

	public MoneySlot getMoneySlot() {
		return moneySlot;
	}

	public void setVendingMachineState(VendingMachineState vendingMachineState) {
		this.vendingMachineState = vendingMachineState;
	}
}
