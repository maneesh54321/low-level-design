package org.vendingmachine.machine;

import java.util.List;

public class VendingMachine {

	private final List<Rack> inventory;

	private final VendingMachineDisplay vendingMachineDisplay;

	public VendingMachine(List<Rack> inventory, VendingMachineDisplay vendingMachineDisplay) {
		this.inventory = inventory;
		this.vendingMachineDisplay = vendingMachineDisplay;
	}

	void addBalance(int balance) {

	}

	public Product dispense(){
		return null;
	}
}
