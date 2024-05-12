package org.vendingmachine.v2.money;

import java.util.List;

public class MoneyDispenser {
	public void dispense(List<Denomination> denominations) {
		System.out.println("Dispensed denominations!!!");
		denominations.forEach(denomination -> System.out.println(denomination.value()));
	}
}
