package com.senior.plan2task.GroupService.Model;

import java.util.List;

import com.senior.plan2task.GroupService.User.User;

public class GroupResponse {

    private String groupId;

    private String groupName;

    private String groupImage;

    private List<Member> members;

    public GroupResponse() {
    }

    public GroupResponse(String groupId, String groupName, String groupImage, List<Member> members) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupImage = groupImage;
        this.members = members;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public List<Member> getMembers() {
        return this.members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }


    @Override
    public String toString() {
        return "{" +
            " groupId='" + getGroupId() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", groupImage='" + getGroupImage() + "'" +
            ", members='" + getMembers() + "'" +
            "}";
    }

}