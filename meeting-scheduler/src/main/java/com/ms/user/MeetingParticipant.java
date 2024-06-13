package com.ms.user;

import com.ms.Calendar;
import com.ms.notification.MeetingEventNotification;

public interface MeetingParticipant {
    void receive(MeetingEventNotification meetingEventNotification);
    String getId();
    Calendar getCalendar();
    void respond();
}
