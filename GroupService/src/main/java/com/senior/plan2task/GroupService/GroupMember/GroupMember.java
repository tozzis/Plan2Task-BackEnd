package com.senior.plan2task.GroupService.GroupMember;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groupMembers")

public class GroupMember {
    @Id
    private String id;
    
    private String groupId;
    
    private String position;
    
    private String userId;

    public GroupMember() {
    }

    public GroupMember(String id, String groupId, String position, String userId) {
        this.id = id;
        this.groupId = groupId;
        this.position = position;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}