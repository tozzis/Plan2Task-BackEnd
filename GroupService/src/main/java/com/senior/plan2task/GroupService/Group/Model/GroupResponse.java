package com.senior.plan2task.GroupService.Group.Model;

import java.util.List;

public class GroupResponse {
    
    private String id;

    private String groupName;
    
    private String groupImage;
    
    private List<Member> member;

    public GroupResponse() {
    }

    public GroupResponse(String id, String groupName, String groupImage, List<Member> member) {
        this.id = id;
        this.groupName = groupName;
        this.groupImage = groupImage;
        this.member = member;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }
    
}
