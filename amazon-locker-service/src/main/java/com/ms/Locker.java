package com.ms;

import java.util.List;

public class Locker {
	private int id;
	private LockerSize size;
	private LockerHub lockerHub;
	private LockerState state;
	private List<Item> items;

	public List<Item> getItems() {
		return items;
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
}
