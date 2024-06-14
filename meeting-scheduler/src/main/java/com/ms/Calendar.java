package com.ms;

import com.ms.meeting.Meeting;
import com.ms.notification.MeetingEventNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Calendar {

    private final List<Meeting> meetings;

    private final List<MeetingEventNotification> pendingInvites;

    public Calendar() {
        meetings = new ArrayList<>();
        pendingInvites = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting) {
        this.meetings.remove(meeting);
    }

    public void addPendingMeeting(MeetingEventNotification meetingEventNotification){
        this.pendingInvites.add(meetingEventNotification);
    }

    public List<MeetingEventNotification> getPendingInvites(){
        return pendingInvites;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "meetings=" + meetings +
                ", pendingInvites=" + pendingInvites +
                '}';
    }

    public Optional<Meeting> getMeetingById(Integer id) {
        return meetings.stream().filter(meeting -> meeting.getId() == id).findFirst();
    }
}
