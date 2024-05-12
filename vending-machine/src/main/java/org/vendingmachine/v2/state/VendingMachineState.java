package org.vendingmachine.v2.state;

import org.vendingmachine.v2.money.Denomination;

public interface VendingMachineState {
	void dispenseProduct();
	void insertMoney(Denomination denomination);
}
