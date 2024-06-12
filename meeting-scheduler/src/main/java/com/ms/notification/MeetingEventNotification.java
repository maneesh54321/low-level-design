package com.ms.notification;

import com.ms.meeting.Meeting;

public abstract class MeetingEventNotification {

    private final Meeting meeting;

    public MeetingEventNotification(Meeting meeting) {
        this.meeting = meeting;
    }

    public Meeting getMeeting(){
        return meeting;
    }
}
