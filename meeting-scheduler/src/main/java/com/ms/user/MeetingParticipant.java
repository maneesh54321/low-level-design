package com.ms.user;

import com.ms.notification.MeetingEventNotification;

public interface MeetingParticipant {
    void receive(MeetingEventNotification meetingEventNotification);
    void respond(MeetingEventNotification meetingEventNotification);
}
