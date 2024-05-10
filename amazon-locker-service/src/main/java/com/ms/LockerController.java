package com.ms;

import com.ms.notification.Notification;
import com.ms.notification.NotificationGateway;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LockerController {

	private final LockerService lockerService;

	private final NotificationGateway notificationGateway;

	public LockerController(LockerService lockerService, NotificationGateway notificationGateway) {
		this.lockerService = lockerService;
		this.notificationGateway = notificationGateway;
	}

	public void chooseHub(Address address, Order order) {
		try(Scanner sc = new Scanner(System.in)) {
			boolean lockerFound = false;
			while (!lockerFound) {
				System.out.println("Choose a locker hub from following:");
				List<LockerHub> nearbyLockersHubs = lockerService.getNearbyLockersHubs(address);
				int count = 1;
				for (LockerHub lockerHub: nearbyLockersHubs) {
					System.out.println(count++ + ". " + lockerHub.toString());
				}
				int choice = Integer.parseInt(sc.nextLine());
				lockerFound = lockerService.reserveLocker(nearbyLockersHubs.get(choice-1), order);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<LockerPackage> finalizeLocker(Order order) {
		return lockerService.assignLockers(order);
	}

	public void cancelLockerReservation(Order order) {
		lockerService.cancelLockerReservation(order);
	}

	public void placePackageInLocker(Order order, List<LockerPackage> lockers) {
		System.out.println("Placed items in locker.");
		var notification = new Notification("");
		// lock the locker.
		Map<LockerPackage, String> lockerCodeMap = lockerService.lockAndGenerateCode(lockers);
		// send notification to customer.
		var message = buildNotificationMessage(order, lockerCodeMap);
		notificationGateway.notify(order.getCustomer(), new Notification(message));
	}

	private String buildNotificationMessage(Order order, Map<LockerPackage, String> lockerCodeMap) {
		StringBuilder sb = new StringBuilder();
		sb.append("Your order has been placed in locker. Following are the details of the lockers:");
		lockerCodeMap.forEach(((lockerPackage, code) ->
			sb.append("Item: ").append(lockerPackage.item())
					.append(", locker: ").append(lockerPackage.locker())
					.append(", code: ").append(code)));
		sb.append("DO NOT SHARE the code with anyone.");
		return sb.toString();
	}
}
