package com.ms;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockerService {

	private final LockerRepository lockerRepository;

	private final Map<Locker, Reservation> reservedLockers;

	private final Lock lock = new ReentrantLock();

	private final LockerHubFinder lockerHubFinder;

	private final LockerFinder lockerFinder;

	public LockerService(LockerRepository lockerRepository, LockerHubFinder lockerHubFinder, LockerFinder lockerFinder) {
		this.lockerRepository = lockerRepository;
		this.lockerHubFinder = lockerHubFinder;
		this.lockerFinder = lockerFinder;
		this.reservedLockers = new HashMap<>();
	}

	public List<LockerHub> getNearbyLockersHubs(Address address) {
		return lockerHubFinder.findNearbyLockerHubs(address, lockerRepository.getAllLockerHubs());
	}

	public boolean reserveLocker(LockerHub lockerHub, Order order) {
		lock.lock();
		try {
			Optional<List<Locker>> lockers = lockerFinder.findLocker(lockerHub, reservedLockers, order.getItems());
			if(lockers.isEmpty() || lockers.get().isEmpty()) return false;
			var reservation = new Reservation(order, LocalDateTime.now());
			lockers.ifPresent(lockerList -> lockerList.forEach(locker -> reservedLockers.put(locker, reservation)));
			return true;
		} finally {
			lock.unlock();
		}
	}

	public void assignLocker(Order order) {
		reservedLockers.entrySet().iterator().forEachRemaining((entry) -> {
			if(entry.getValue().order().equals(order)) {
				entry.getKey().assign();
				reservedLockers.remove(entry.getKey());
			}
		});
	}

	public void releaseLocker(Order order) {
		reservedLockers.entrySet().iterator().forEachRemaining((entry) -> {
			if(entry.getValue().order().equals(order)) {
				reservedLockers.remove(entry.getKey());
			}
		});
	}
}
