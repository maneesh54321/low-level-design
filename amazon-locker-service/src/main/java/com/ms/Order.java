package com.ms;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private List<Item> items;
	private LocalDateTime orderDate;
	private Address deliveryAddress;
	private Address shippingAddress;
	private Customer customer;
}
