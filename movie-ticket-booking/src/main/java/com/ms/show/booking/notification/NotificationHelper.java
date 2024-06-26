package com.ms.show.booking.notification;

import com.ms.show.booking.user.User;
import java.util.List;

public class NotificationHelper implements Notifier {

	private final List<Notifier> notifiers;

	public NotificationHelper(List<Notifier> notifiers) {
		this.notifiers = notifiers;
	}

	@Override
	public void notify(User user) {

	}
}
