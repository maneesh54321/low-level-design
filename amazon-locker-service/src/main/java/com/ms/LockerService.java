package com.ms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockerService {

	private final LockerRepository lockerRepository;

	private final Map<String, List<Locker>> reservedLockers;

	private final Lock lock = new ReentrantLock();

	public LockerService(LockerRepository lockerRepository) {
		this.lockerRepository = lockerRepository;
		reservedLockers = new HashMap<>();
	}

	public List<LockerHub> getNearbyLockersHubs(Address address) {
		return List.of();
	}

	public boolean reserveLocker(LockerHub lockerHub, Order order) {
		lock.lock();
		try {
			Optional<List<Locker>> lockers = findLocker(lockerHub, order.getItems());
			if(lockers.isEmpty()) return false;
			reservedLockers.put(order.getId(), lockers.get());
			return true;
		} finally {
			lock.unlock();
		}
	}

	private Optional<List<Locker>> findLocker(LockerHub lockerHub, List<Item> items) {
		return Optional.empty();
	}

	public void assignLocker(Order order) {

	}

	public void releaseLocker(Order order) {

	}
}
