package com.senior.plan2task.GroupService.Model;

import com.senior.plan2task.GroupService.User.User;

public class Member {
    private User user;
    private String position;

    public Member() {
    }

    public Member(User user, String position) {
        this.user = user;
        this.position = position;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            ", position='" + getPosition() + "'" +
            "}";
    }
}