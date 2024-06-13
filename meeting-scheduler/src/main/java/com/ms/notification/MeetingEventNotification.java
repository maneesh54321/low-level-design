package com.ms.notification;

import com.ms.meeting.Meeting;

public abstract class MeetingEventNotification {

    private final String id;

    private final Meeting meeting;

    public MeetingEventNotification(String id, Meeting meeting) {
        this.id = id;
        this.meeting = meeting;
    }

    public Meeting getMeeting(){
        return meeting;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MeetingEventNotification{" +
                "id='" + id + '\'' +
                ", meeting=" + meeting +
                '}';
    }
}
