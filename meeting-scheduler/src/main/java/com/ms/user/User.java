package com.ms.user;

import com.ms.Calendar;
import com.ms.meeting.Meeting;

public class User extends MeetingUser {

    private String id;

    private String name;

    public User(Calendar calendar) {
        super(calendar);
    }

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
