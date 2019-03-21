package com.senior.plan2task.PlanService.User;

class UserRequest {
    
    private String Id;

    public UserRequest() {
    }

    public UserRequest(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
    
}
