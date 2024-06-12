package com.ms.meeting;

import com.ms.Interval;
import com.ms.room.MeetingRoomRepository;
import com.ms.user.MeetingOrganizer;
import com.ms.user.MeetingParticipant;
import com.ms.user.UserRepository;

public class MeetingScheduler {

    private final MeetingRoomRepository meetingRoomRepository;

    private final UserRepository userRepository;

    public MeetingScheduler(MeetingRoomRepository meetingRoomRepository, UserRepository userRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.userRepository = userRepository;
    }

    public boolean scheduleMeeting(MeetingOrganizer organizer, Interval interval, MeetingParticipant[] participants) {
        return false;
    }

    public void cancelMeeting(Meeting meeting) {

    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
