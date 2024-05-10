package com.ms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			var lockers = lockerFinder.findLocker(lockerHub, reservedLockers, order.getItems());
			if (lockers.isEmpty() || lockers.get().isEmpty()) return false;
			lockers.ifPresent(lockerList -> lockerList.forEach((item, locker) -> {
				var reservation = new Reservation(order, LocalDateTime.now(), item);
				reservedLockers.put(locker, reservation);
			}));
			return true;
		} finally {
			lock.unlock();
		}
	}

	public List<LockerPackage> assignLockers(Order inputOrder) {
		var lockerPackages = new ArrayList<LockerPackage>();
		reservedLockers.entrySet().iterator().forEachRemaining((entry) -> {
			var reservation = entry.getValue();
			if ( reservation.order().equals(inputOrder)) {
				Locker locker = entry.getKey();
				locker.assign();
				lockerPackages.add(new LockerPackage(reservation.order(), locker, reservation.item()));
				reservedLockers.remove(locker);
			}
		});
		return lockerPackages;
	}

	public void cancelLockerReservation(Order order) {
		reservedLockers.entrySet().iterator().forEachRemaining((entry) -> {
			if (entry.getValue().order().equals(order)) {
				reservedLockers.remove(entry.getKey());
			}
		});
	}

	public Map<LockerPackage, String> lockAndGenerateCode(List<LockerPackage> lockerPackages) {
		var result = new HashMap<LockerPackage, String>();
		lockerPackages.forEach(lockerPackage -> {
			lockerPackage.locker().addItem(lockerPackage.item());
			String code = lockerPackage.locker().lock();
			result.put(lockerPackage, code);
		});
		return result;
	}
}
