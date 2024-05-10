package com.ms.notification;

import com.ms.Customer;

public interface Notifier {
	void notify(Customer customer, Notification notification);
}
