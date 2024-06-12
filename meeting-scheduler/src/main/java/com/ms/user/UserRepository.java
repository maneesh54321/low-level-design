package com.ms.user;

public class UserRepository {

    private final User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getAllUsers(){
        return this.users;
    }
}
