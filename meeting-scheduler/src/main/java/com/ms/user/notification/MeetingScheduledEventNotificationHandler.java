package com.ms.user.notification;

import com.ms.notification.MeetingEventNotification;
import com.ms.user.MeetingUser;

public class MeetingScheduledEventNotificationHandler implements MeetingEventNotificationHandler {
    @Override
    public void handleNotification(MeetingEventNotification eventNotification, MeetingUser meetingUser) {
        System.out.println("User: " + meetingUser);
        System.out.println("Received notification !!!" +  eventNotification);
    }
}
