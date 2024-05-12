package org.vendingmachine.v2.product;

import java.util.List;

public class ProductInventory {

	private final List<Rack> racks;
	private final ProductDispenser productDispenser;

	public ProductInventory(List<Rack> racks, ProductDispenser productDispenser) {
		this.racks = racks;
		this.productDispenser = productDispenser;
	}

	public void dispenseProduct(int rackId) {
		var product = this.racks.stream().filter(rack -> rack.getId() == rackId).findFirst().map(
				Rack::dispenseProduct).orElse(null);
		productDispenser.dispense(product);
	}

	public boolean hasProduct(int rackId) {
		return this.racks.stream().filter(rack -> rack.getId() == rackId).findFirst()
				.map(Rack::hasProduct).orElse(false);
	}

	public int getPrice(int rackId) {
		return this.racks.stream().filter(rack -> rack.getId() == rackId).findFirst()
				.map(Rack::getPrice).orElseThrow();
	}
}
