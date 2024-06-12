package com.ms.user;

import com.ms.Calendar;
import com.ms.meeting.MeetingScheduler;

public abstract class MeetingUser implements MeetingParticipant, MeetingOrganizer {

    protected Calendar calendar;

    protected MeetingScheduler scheduler;

    public MeetingUser(Calendar calendar) {
        this.calendar = calendar;
    }
}
