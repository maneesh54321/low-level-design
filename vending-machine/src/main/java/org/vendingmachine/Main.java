package org.vendingmachine;

import java.util.ArrayList;
import java.util.List;
import org.vendingmachine.v2.Display;
import org.vendingmachine.v2.SelectionPad;
import org.vendingmachine.v2.VendingMachine;
import org.vendingmachine.v2.money.Balance;
import org.vendingmachine.v2.money.Denomination;
import org.vendingmachine.v2.money.MoneyDispenser;
import org.vendingmachine.v2.money.MoneyManager;
import org.vendingmachine.v2.money.MoneySlot;
import org.vendingmachine.v2.money.Value;
import org.vendingmachine.v2.product.Product;
import org.vendingmachine.v2.product.ProductDispenser;
import org.vendingmachine.v2.product.ProductInventory;
import org.vendingmachine.v2.product.Rack;

public class Main {

	public static void main(String[] args) {
		List<Rack> racks = new ArrayList<>();
		racks.add(new Rack(1, new Product(10, "Lays Green"), 5));
		racks.add(new Rack(2, new Product(10, "Lays Blue"), 5));
		racks.add(new Rack(3, new Product(10, "Lays Red"), 5));
		racks.add(new Rack(4, new Product(10, "Lays Chilli"), 5));
		racks.add(new Rack(5, new Product(15, "Haldiram's Bhujiya"), 5));
		racks.add(new Rack(6, new Product(10, "Roasted Peanuts"), 5));
		racks.add(new Rack(7, new Product(20, "Munch"), 5));
		racks.add(new Rack(8, new Product(40, "kitkat"), 5));
		racks.add(new Rack(9, new Product(20, "kitkat"), 5));
		racks.add(new Rack(10, new Product(10, "Dairy milk"), 5));
		ProductInventory inventory = new ProductInventory(racks, new ProductDispenser());

		ArrayList<Denomination> denominationList = new ArrayList<>();
		denominationList.add(new Denomination(Value.TEN));
		denominationList.add(new Denomination(Value.TEN));
		denominationList.add(new Denomination(Value.TEN));
		denominationList.add(new Denomination(Value.TWENTY));
		denominationList.add(new Denomination(Value.TWENTY));
		denominationList.add(new Denomination(Value.FIVE));
		denominationList.add(new Denomination(Value.FIVE));
		denominationList.add(new Denomination(Value.FIVE));
		denominationList.add(new Denomination(Value.FIVE));
		denominationList.add(new Denomination(Value.TWO));
		denominationList.add(new Denomination(Value.TWO));
		denominationList.add(new Denomination(Value.ONE));
		denominationList.add(new Denomination(Value.ONE));
		denominationList.add(new Denomination(Value.ONE));
		denominationList.add(new Denomination(Value.ONE));
		denominationList.add(new Denomination(Value.ONE));
		denominationList.add(new Denomination(Value.ONE));
		MoneyManager moneyManager = new MoneyManager(new MoneyDispenser(), new Balance(100,
				denominationList));

		VendingMachine vendingMachine = new VendingMachine(inventory, moneyManager, new SelectionPad(),
				new Display(), new MoneySlot());

		System.out.println("Insert money");
		System.out.println("Inserting money...");
		vendingMachine.insertMoney(new Denomination(Value.INVALID));
		System.out.println("Trying to Dispense..");
		vendingMachine.dispenseProduct();
	}
}