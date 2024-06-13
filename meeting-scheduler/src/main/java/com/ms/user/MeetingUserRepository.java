package com.ms.user;

import java.util.List;
import java.util.Optional;

public class MeetingUserRepository {

    private final List<MeetingUser> users;

    public MeetingUserRepository(List<MeetingUser> users) {
        this.users = users;
    }

    public List<MeetingUser> getAllUsers(){
        return this.users;
    }

    public Optional<MeetingUser> getUserById(String participantId) {
        return users.stream().filter(user -> user.getId().equals(participantId)).findFirst();
    }
}
