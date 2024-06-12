package com.ms.user;

import java.util.Arrays;
import java.util.Optional;

public class MeetingUserRepository {

    private final MeetingUser[] users;

    public MeetingUserRepository(MeetingUser[] users) {
        this.users = users;
    }

    public MeetingUser[] getAllUsers(){
        return this.users;
    }

    public Optional<MeetingUser> getUserById(String participantId) {
        return Arrays.stream(users).filter(user -> user.getId().equals(participantId)).findFirst();
    }
}
