package com.ms.user;

import com.ms.meeting.Meeting;

public interface MeetingParticipant {
    void receive(Meeting meeting);
    void accept(Meeting meeting);
    void reject(Meeting meeting);
}
