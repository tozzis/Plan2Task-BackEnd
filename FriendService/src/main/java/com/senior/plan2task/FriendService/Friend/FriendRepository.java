package com.senior.plan2task.FriendService.Friend;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendRepository extends MongoRepository<Friend, String> {
    public List<Friend> findByUserId(String userId);
    public Friend findByUserIdAndFriendId(String userId,String friendId);
}
