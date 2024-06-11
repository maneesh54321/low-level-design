package com.ms.user;

import com.ms.meeting.Meeting;

public class User implements MeetingOrganizer, MeetingParticipant{
    private String id;
    private String name;

    @Override
    public void createMeeting() {

    }

    @Override
    public void cancelMeeting() {

    }

    @Override
    public void receive(Meeting meeting) {

    }

    @Override
    public void accept(Meeting meeting) {

    }

    @Override
    public void reject(Meeting meeting) {

    }
}
