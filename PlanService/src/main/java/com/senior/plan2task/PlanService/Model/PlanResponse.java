package com.senior.plan2task.PlanService.Model;

import com.senior.plan2task.PlanService.User.User;
import java.time.LocalDate;
import java.util.List;

public class PlanResponse {
    
    private String id;
    
    private String title;
    
    private String detail;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private Location location;
    
    private List<User> member;
    
    private String type;
    
    private boolean status;
    
    private Notification notification;
    
    private String userId;
}
