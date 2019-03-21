package com.senior.plan2task.PlanService.Model;

public class Member {
    
    private String userId;

    public Member() {
    }

    public Member(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
