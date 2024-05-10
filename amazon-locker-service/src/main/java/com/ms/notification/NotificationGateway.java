package com.ms.notification;

import com.ms.Customer;

import java.util.List;

public class NotificationGateway implements Notifier {

	private final List<Notifier> notifiers;

	public NotificationGateway(List<Notifier> notifiers) {
		this.notifiers = notifiers;
	}

	@Override
	public void notify(Customer customer, Notification notification) {
		notifiers.forEach(notifier -> notifier.notify(customer, notification));
	}
}
