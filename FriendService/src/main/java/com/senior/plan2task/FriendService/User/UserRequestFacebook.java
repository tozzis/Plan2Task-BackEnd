package com.senior.plan2task.FriendService.User;

public class UserRequestFacebook {
    
    private long facebookId;

    public UserRequestFacebook() {
    }

    public UserRequestFacebook(long facebookId) {
        this.facebookId = facebookId;
    }

    public long getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(long facebookId) {
        this.facebookId = facebookId;
    }
    
}
