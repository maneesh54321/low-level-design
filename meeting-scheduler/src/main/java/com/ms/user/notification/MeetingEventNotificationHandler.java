package com.ms.user.notification;

import com.ms.notification.CancelledMeetingEventNotification;
import com.ms.notification.MeetingEventNotification;
import com.ms.notification.NewMeetingEventNotification;
import com.ms.user.MeetingUser;

import java.util.Map;

public interface MeetingEventNotificationHandler {

    Map<Class<? extends MeetingEventNotification>, MeetingEventNotificationHandler> handlers = Map.of(
            NewMeetingEventNotification.class, new MeetingScheduledEventNotificationHandler(),
            CancelledMeetingEventNotification.class, new CancelledMeetingEventNotificationHandler()
    );

    default void handleNotification(MeetingEventNotification eventNotification, MeetingUser meetingUser) {
        handlers.get(eventNotification.getClass()).handleNotification(eventNotification, meetingUser);
    }
}
