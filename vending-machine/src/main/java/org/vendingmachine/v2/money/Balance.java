package org.vendingmachine.v2.money;

import java.util.List;

public class Balance {
	private int amount;
	private final List<Denomination> denominationList;

	public Balance(int amount, List<Denomination> denominationList) {
		this.amount = amount;
		this.denominationList = denominationList;
	}

	public int getAmount() {
		return amount;
	}

	public void resetToZero(){
		this.amount = 0;
		this.denominationList.clear();
	}

	public List<Denomination> getDenominationList() {
		return denominationList;
	}

	public void add(List<Denomination> denominations){
		for (Denomination denomination: denominations) {
			denominations.add(denomination);
			amount += denomination.getValue();
		}
	}

	public void take(List<Denomination> denominations) {
		for (Denomination denomination: denominations) {
			denominations.remove(denomination);
			amount -= denomination.getValue();
		}
	}
}
