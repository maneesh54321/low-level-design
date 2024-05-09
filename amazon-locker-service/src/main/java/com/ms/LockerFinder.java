package com.ms;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LockerFinder {
	Optional<List<Locker>> findLocker(LockerHub lockerHub, Map<Locker, Reservation> reservedLockers, List<Item> items);
}
