package com.senior.plan2task.FriendService.Friend;

import com.senior.plan2task.FriendService.Filter.TokenAuthenticationService;
import com.senior.plan2task.FriendService.User.User;
import com.senior.plan2task.FriendService.User.UserAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FriendController {
    
    @Autowired
    private UserAdapter userAdapter;
    
    @Autowired
    private FriendService friendService;
    
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    
    @GetMapping("/friends")
    public ResponseEntity<List<User>> getFriendByUserId(HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<Friend> friends = friendService.getFriendByUserId(userId);
        List<User> user = new ArrayList<>();
        if(!friends.isEmpty()) {
            for (int i = 0; i < friends.size(); i++) {
                user.add(userAdapter.getUserById(request, friends.get(i).getFriendId()));
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
    
    @PostMapping("/friend")
    public ResponseEntity<Friend> AddFriend(HttpServletRequest request, @RequestBody Map<String, String> friend) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        String friendId = friend.get("friendId");
        Friend friendCheck = friendService.getFriendByUserIdAndFriendId(userId, friendId);
        if(friendCheck==null) {
            Friend friendDetail = new Friend(null, userId, friendId);
            friendService.createFriend(friendDetail);
            return new ResponseEntity<>(friendDetail, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have already added a friend !!!");
        }
    }
    
     @PostMapping("/friend/email")
    public ResponseEntity<FriendCheckEmailResponse> CheckFriendByEmail(HttpServletRequest request, @RequestBody Map<String, String> friend) {
        String friendEmail = friend.get("email");
        User friendDetail = userAdapter.getUserByEmail(request, friendEmail);
        if(friendDetail!=null){
            String userId = tokenAuthenticationService.getUserByToken(request);
            Friend friendCheck = friendService.getFriendByUserIdAndFriendId(userId, friendDetail.getId());
            if(friendCheck!=null){
                return new ResponseEntity<>(new FriendCheckEmailResponse(friendDetail, true), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new FriendCheckEmailResponse(friendDetail, false), HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
    
    @DeleteMapping("/friend")
    public ResponseEntity<Friend> DeleteFriend(HttpServletRequest request, @RequestBody Map<String, String> friend) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        String friendId = friend.get("friendId");
        Friend friendDetail = friendService.getFriendByUserIdAndFriendId(userId, friendId);
        friendService.deleteFriend(friendDetail.getId());
        return new ResponseEntity<>(friendDetail, HttpStatus.OK);
    }
}
