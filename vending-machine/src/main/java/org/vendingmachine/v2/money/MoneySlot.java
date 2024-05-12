package org.vendingmachine.v2.money;

import java.util.Optional;

public class MoneySlot {

	public Optional<Denomination> insertMoney(Denomination denomination){
		if(!isValid(denomination)) {
			expelMoney(denomination);
			return Optional.empty();
		}
		return Optional.of(denomination);
	}

	private boolean isValid(Denomination denomination) {
		return denomination.isValid();
	}

	private void expelMoney(Denomination denomination) {
		System.out.println("Rejecting denomination: " + denomination);
	}

}
