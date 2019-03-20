package com.senior.plan2task.FriendService.Friend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "friends")
public class Friend {
    
    @Id
    private String id;
    
    private String userId;
    
    private String friendId;

    public Friend() {
    }

    public Friend(String id, String userId, String friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
    
}
