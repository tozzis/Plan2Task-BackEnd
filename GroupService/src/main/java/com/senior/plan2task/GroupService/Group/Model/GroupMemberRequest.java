package com.senior.plan2task.GroupService.Group.Model;

import java.util.List;

public class GroupMemberRequest {
    
    private List<GroupMemberStatus> groupMemberStatus;

    public GroupMemberRequest() {
    }

    public GroupMemberRequest(List<GroupMemberStatus> groupMemberStatus) {
        this.groupMemberStatus = groupMemberStatus;
    }

    public List<GroupMemberStatus> getGroupMemberStatus() {
        return groupMemberStatus;
    }

    public void setGroupMemberStatus(List<GroupMemberStatus> groupMemberStatus) {
        this.groupMemberStatus = groupMemberStatus;
    }
    
}