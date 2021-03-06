package com.senior.plan2task.PlanService.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalValue {
    
    public static String secretKey;
    
    public static String userService;

    @Value("${authenservice.jwt.secret}")
    public void setSecretKey(String secretKey) {
        GlobalValue.secretKey = secretKey;
    }

    @Value("${authenservice.url.userService}")
    public void setUserService(String userService) {
        GlobalValue.userService = userService;
    }
    
}
