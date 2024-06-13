package com.ms.meeting;

import com.ms.Interval;
import com.ms.notification.CancelledMeetingEventNotification;
import com.ms.notification.NewMeetingEventNotification;
import com.ms.room.MeetingRoom;
import com.ms.room.MeetingRoomRepository;
import com.ms.user.MeetingOrganizer;
import com.ms.user.MeetingParticipant;
import com.ms.user.MeetingUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MeetingScheduler {

    private final MeetingRoomRepository meetingRoomRepository;

    private final MeetingUserRepository meetingUserRepository;

    public MeetingScheduler(MeetingRoomRepository meetingRoomRepository, MeetingUserRepository meetingUserRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingUserRepository = meetingUserRepository;
    }

    public boolean scheduleMeeting(MeetingOrganizer organizer, String agenda, Interval interval, List<MeetingParticipant> participants) {
        System.out.printf("Scheduling meeting with \norganizer: %s\n interval: %s\n& participants %s\n", organizer, interval, participants);
        Optional<MeetingRoom> availableMeetingRoom = meetingRoomRepository.getAvailableMeetingRoom(interval, participants.size() + 1);
        if (availableMeetingRoom.isPresent()) {
            // create a meeting
            MeetingRoom meetingRoom = availableMeetingRoom.get();
            var meeting = new Meeting(1, interval, agenda, meetingRoom, new ArrayList<>(participants), organizer);
            // book a room
            meetingRoom.book(meeting);
            System.out.println("Meeting room " + meetingRoom + " booked successfully!!!");
            // send invites
            System.out.println("Sending notification to all participants..");
            participants.forEach(participant -> participant.receive(new NewMeetingEventNotification("1", meeting)));
            return true;
        } else {
            return false;
        }
    }

    public void cancelMeeting(Meeting meeting) {
        System.out.println("Cancelling meeting: " + meeting);
        meeting.getOrganizer().getCalendar().removeMeeting(meeting);
        System.out.println("Meeting cancelled by Organizer!!!");
        System.out.println("Sending notifications to participants..");
        meeting.getMeetingParticipants().forEach(participant -> participant.receive(new CancelledMeetingEventNotification("2", meeting)));
    }

    public MeetingUserRepository getUserRepository() {
        return meetingUserRepository;
    }
}
