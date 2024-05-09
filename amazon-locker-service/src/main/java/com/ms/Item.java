package com.ms;

public class Item {
	private final String name;
	private final double price;
	private final Order order;
	private final ItemSize itemSize;
	private final int quantity;

	public Item(String name, double price, Order order, ItemSize itemSize, int quantity) {
		this.name = name;
		this.price = price;
		this.order = order;
		this.itemSize = itemSize;
		this.quantity = quantity;
	}

	public ItemSize getItemSize() {
		return itemSize;
	}

	public int getQuantity() {
		return quantity;
	}

	public Order getOrder() {
		return order;
	}
}
