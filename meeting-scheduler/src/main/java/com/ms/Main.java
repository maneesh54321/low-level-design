package com.ms;

import com.ms.meeting.MeetingScheduler;
import com.ms.room.MeetingRoom;
import com.ms.room.MeetingRoomRepository;
import com.ms.user.MeetingUser;
import com.ms.user.MeetingUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        try{
            Calendar calendar = new Calendar();
            List<MeetingRoom> meetingRooms = new ArrayList<>();
            var meetingRoom = new MeetingRoom(4, "4D", 15);
            meetingRooms.add(meetingRoom);
            var meetingRoomRepository = new MeetingRoomRepository(meetingRooms);
            List<MeetingUser> users = new ArrayList<>();
            var meetingUserRepository = new MeetingUserRepository(users);
            var meetingScheduler = new MeetingScheduler(meetingRoomRepository, meetingUserRepository);

            var mohan = new MeetingUser("1", "Mohan", calendar, meetingScheduler);
            var ram = new MeetingUser("2", "Ram", calendar, meetingScheduler);
            var sita = new MeetingUser("3", "Sita", calendar, meetingScheduler);
            var john = new MeetingUser("4", "John", calendar, meetingScheduler);
            var david = new MeetingUser("5", "David", calendar, meetingScheduler);

            users.add(mohan);
            users.add(ram);
            users.add(sita);
            users.add(john);
            users.add(david);

            mohan.createMeeting();
            mohan.cancelMeeting();
        } finally {
            SC.close();
        }
    }
}