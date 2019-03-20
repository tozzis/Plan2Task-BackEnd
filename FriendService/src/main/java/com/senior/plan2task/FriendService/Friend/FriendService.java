package com.senior.plan2task.FriendService.Friend;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    
    @Autowired
    private FriendRepository friendRepository;
    

    public List<Friend> getFriendByUserId(String userId) {
        return friendRepository.findByUserId(userId);
    }
    
    public Friend getFriendById(String id){
        return friendRepository.findById(id).get();
    }
    
    public void createFriend(Friend friend) {
        friendRepository.save(friend);
    }
    
    public void deleteFriend(String id) {
        friendRepository.deleteById(id);
    }
    
}
