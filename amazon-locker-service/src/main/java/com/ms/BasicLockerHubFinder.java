package com.ms;

import java.util.List;

public class BasicLockerHubFinder implements LockerHubFinder {
	@Override
	public List<LockerHub> findNearbyLockerHubs(Address address, List<LockerHub> lockerHubs) {
		return lockerHubs;
	}
}
