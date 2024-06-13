package com.ms.notification;

import com.ms.meeting.Meeting;

public class CancelledMeetingEventNotification extends MeetingEventNotification {

    public CancelledMeetingEventNotification(String id, Meeting meeting) {
        super(id, meeting);
    }
}
