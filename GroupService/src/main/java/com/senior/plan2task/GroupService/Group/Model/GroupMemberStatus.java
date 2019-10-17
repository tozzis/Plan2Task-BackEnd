package com.senior.plan2task.GroupService.Group.Model;

public class GroupMemberStatus {
    
    private String userId;
    
    private boolean status;

    public GroupMemberStatus() {
    }

    public GroupMemberStatus(String userId, boolean status) {
        this.userId = userId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
