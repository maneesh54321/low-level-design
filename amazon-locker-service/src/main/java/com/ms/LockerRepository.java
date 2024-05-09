package com.ms;

import java.util.List;

public class LockerRepository {

	private final List<LockerHub> lockerHubs;

	public LockerRepository(List<LockerHub> lockerHubs) {
		this.lockerHubs = lockerHubs;
	}

	public List<LockerHub> getAllLockerHubs() {
		return lockerHubs;
	}
}
