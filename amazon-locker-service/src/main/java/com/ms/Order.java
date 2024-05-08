package com.ms;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private String id;
	private List<Item> items;
	private LocalDateTime orderDate;
	private Address deliveryAddress;
	private Address shippingAddress;
	private Customer customer;

	public String getId() {
		return id;
	}

	public List<Item> getItems() {
		return items;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public Customer getCustomer() {
		return customer;
	}
}
