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

    public PlanResponse() {
    }

    public PlanResponse(String id, String title, String detail, LocalDate startDate, LocalDate endDate, Location location, List<User> member, String type, boolean status, Notification notification, String userId) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.member = member;
        this.type = type;
        this.status = status;
        this.notification = notification;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<User> getMember() {
        return member;
    }

    public void setMember(List<User> member) {
        this.member = member;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
