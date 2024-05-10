package com.ms;

import java.util.ArrayList;
import java.util.List;

public class Locker {
	private final int id;
	private final LockerSize size;
	private LockerState state;
	private final List<Item> items;
	private final LockerHub lockerHub;

	public Locker(int id, LockerSize size, LockerState state, LockerHub lockerHub) {
		this.id = id;
		this.size = size;
		this.state = state;
		this.items = new ArrayList<>();
		this.lockerHub = lockerHub;
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void assign() {
		this.state = LockerState.ASSIGNED;
	}

	public void release() {
		this.state = LockerState.FREE;
	}

	public boolean isFree() {
		return state == LockerState.FREE;
	}

	public String lock() {
		System.out.println("Locker %d locked!!");
		return Util.generateRandomCode();
	}
}
