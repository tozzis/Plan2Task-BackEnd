package com.senior.plan2task.GroupService.Model;

import java.util.List;

import com.senior.plan2task.GroupService.User.User;

public class GroupMemberResponse {

    private String id;
    private String groupId;
    private String groupPosition;
    private User user;
    public GroupMemberResponse() {
    }

    public GroupMemberResponse(String id, String groupId, String groupPosition, User user) {
        this.id = id;
        this.groupId = groupId;
        this.groupPosition = groupPosition;
        this.user = user;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupPosition() {
        return this.groupPosition;
    }

    public void setGroupPosition(String groupPosition) {
        this.groupPosition = groupPosition;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", groupId='" + getGroupId() + "'" +
            ", groupPosition='" + getGroupPosition() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }



   
}