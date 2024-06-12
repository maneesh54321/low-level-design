package com.ms.notification;

import com.ms.meeting.Meeting;

public class CancelledMeetingEventNotification extends MeetingEventNotification {

    public CancelledMeetingEventNotification(Meeting meeting) {
        super(meeting);
    }
}
