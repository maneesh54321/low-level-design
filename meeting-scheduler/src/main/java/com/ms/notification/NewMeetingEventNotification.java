package com.ms.notification;

import com.ms.meeting.Meeting;

public class NewMeetingEventNotification extends MeetingEventNotification {

    public NewMeetingEventNotification(String id, Meeting meeting) {
        super(id, meeting);
    }
}
