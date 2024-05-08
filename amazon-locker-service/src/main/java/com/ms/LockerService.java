package com.ms;

import java.util.List;
import java.util.Map;

public class LockerService {

	private LockerRepository lockerRepository;

	private Map<Order, Locker> reservedLockers;

	public List<LockerHub> getNearbyLockersHubs(Address address) {
		return List.of();
	}

	public boolean reserveLocker(LockerHub lockerHub, Order order) {
		return true;
	}

	public void assignLocker(Order order) {

	}

	public void releaseLocker(Order order) {

	}
}
