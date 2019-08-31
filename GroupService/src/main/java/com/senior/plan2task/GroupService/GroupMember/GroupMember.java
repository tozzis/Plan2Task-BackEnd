package com.senior.plan2task.GroupService.GroupMember;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groupMembers")

public class GroupMember {
    @Id
    private String id;
    private String groupId;
    private String groupPosition;
    private String userId;

    public GroupMember() {
    }

    public GroupMember(String id, String groupId, String groupPosition, String userId) {
        this.id = id;
        this.groupId = groupId;
        this.groupPosition = groupPosition;
        this.userId = userId;
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

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", groupId='" + getGroupId() + "'" + ", groupPosition='"
                + getGroupPosition() + "'" + ", userId='" + getUserId() + "'" + "}";
    }

}