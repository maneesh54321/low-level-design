package com.ms.notification;

import com.ms.meeting.Meeting;

public class NewMeetingEventNotification extends MeetingEventNotification {

    public NewMeetingEventNotification(Meeting meeting) {
        super(meeting);
    }
}
