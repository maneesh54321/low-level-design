package com.ms.user.notification;

import com.ms.notification.MeetingEventNotification;
import com.ms.user.MeetingUser;

public interface MeetingEventNotificationHandler {

    void handleNotification(MeetingEventNotification eventNotification, MeetingUser meetingUser);
}
