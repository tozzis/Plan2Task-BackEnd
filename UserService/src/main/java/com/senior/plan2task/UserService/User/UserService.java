package com.senior.plan2task.UserService.User;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(String id){
        return userRepository.findById(id).get();
    }
    
    public User getUserByFacebookId(long facebookId){
        return userRepository.findByFacebookId(facebookId);
    }
    
    public User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }
    
    public void createUser(User user) {
        userRepository.save(user);
    }
    
}