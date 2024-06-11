package com.ms;

import com.ms.meeting.Meeting;

import java.time.LocalDateTime;
import java.util.List;

public class Calendar {

    private List<Meeting> meetings;

    private List<Meeting> pendingInvites;

    public void getMeetings(LocalDateTime dateTime) {

    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting) {
        this.meetings.remove(meeting);
    }

    public Meeting[] getPendingInvites(){

    }
}
