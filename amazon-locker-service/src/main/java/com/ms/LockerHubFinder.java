package com.ms;

import java.util.List;

public interface LockerHubFinder {

	List<LockerHub> findNearbyLockerHubs(Address address, List<LockerHub> lockerHubs);
}
