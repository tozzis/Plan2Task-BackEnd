package com.senior.plan2task.UserService.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByFacebookId(long facebookId);
    public User findByEmail(String email);
}

