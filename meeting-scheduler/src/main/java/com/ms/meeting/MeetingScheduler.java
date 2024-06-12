package com.ms.meeting;

import com.ms.Interval;
import com.ms.room.MeetingRoomRepository;
import com.ms.user.MeetingOrganizer;
import com.ms.user.MeetingParticipant;
import com.ms.user.MeetingUserRepository;

import java.util.List;

public class MeetingScheduler {

    private final MeetingRoomRepository meetingRoomRepository;

    private final MeetingUserRepository meetingUserRepository;

    public MeetingScheduler(MeetingRoomRepository meetingRoomRepository, MeetingUserRepository meetingUserRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingUserRepository = meetingUserRepository;
    }

    public boolean scheduleMeeting(MeetingOrganizer organizer, Interval interval, List<MeetingParticipant> participants) {
        System.out.printf("Scheduling meeting with \norganizer: %s\n interval: %s\n& participants %s", organizer, interval, participants);
        return false;
    }

    public void cancelMeeting(Meeting meeting) {
        System.out.println("Cancelling meeting: " + meeting);
    }

    public MeetingUserRepository getUserRepository() {
        return meetingUserRepository;
    }
}
