package com.senior.plan2task.GroupService.Group;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groups")
public class Group {
    @Id
    private String id;

    private String groupName;
    private String groupImage;

    public Group() {
    }

    public Group(String id, String groupName, String groupImage) {
        this.id = id;
        this.groupName = groupName;
        this.groupImage = groupImage;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupImage() {
        return this.groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", groupName='" + getGroupName() + "'" + ", groupImage='"
                + getGroupImage() + "'" + "}";
    }

}