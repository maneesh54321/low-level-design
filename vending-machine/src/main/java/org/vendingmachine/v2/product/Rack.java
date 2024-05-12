package org.vendingmachine.v2.product;

import java.util.ArrayList;
import java.util.List;

public class Rack {
	private final int id;
	private final List<Product> products;
	private int price;

	public Rack(int id) {
		this.id = id;
		products = new ArrayList<>();
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
		return !products.isEmpty();
	}

	public void addProduct(Product product){
		products.add(product);
	}

	public Product dispenseProduct(){
		return products.removeFirst();
	}
}
