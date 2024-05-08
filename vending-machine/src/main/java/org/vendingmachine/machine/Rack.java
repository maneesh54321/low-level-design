package org.vendingmachine.machine;

import java.util.List;

public class Rack {
	private final int id;
	private int price;
	private List<Product> products;

	public Rack(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean hasProduct(){
		return products != null && products.isEmpty();
	}

	public void setProducts(List<Product> products) {
		assert products != null;
		this.products = products;
	}

	public void addProduct(Product product) {
		assert products != null;
		this.products.add(product);
	}
}
