package com.ms.user.notification;

import com.ms.notification.MeetingEventNotification;
import com.ms.user.MeetingUser;

public class CancelledMeetingEventNotificationHandler implements MeetingEventNotificationHandler {

    @Override
    public void handleNotification(MeetingEventNotification eventNotification, MeetingUser meetingUser) {
        System.out.println("User: " + meetingUser);
        System.out.println("Received notification: " + eventNotification);
        System.out.println("Removing meeting from calendar..");
        meetingUser.getCalendar().removeMeeting(eventNotification.getMeeting());
        System.out.println("Meeting removed. Updated Calendar:\n" + meetingUser.getCalendar());
    }
}
