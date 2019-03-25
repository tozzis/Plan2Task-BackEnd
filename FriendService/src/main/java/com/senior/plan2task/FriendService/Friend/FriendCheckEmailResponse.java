package com.senior.plan2task.FriendService.Friend;

import com.senior.plan2task.FriendService.User.User;

public class FriendCheckEmailResponse {
    
    private User friend;
    
    private boolean status;

    public FriendCheckEmailResponse() {
    }

    public FriendCheckEmailResponse(User friend, boolean status) {
        this.friend = friend;
        this.status = status;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
