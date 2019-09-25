package com.senior.plan2task.PlanService.Model;

import java.util.Date;

import com.senior.plan2task.PlanService.Plan.Plan;
import com.senior.plan2task.PlanService.User.User;

public class TaskResponseUser {

    private String id;

    private int priority;

    private String title;

    private String detail;

    private Date dateTime;

    private Location location;

    private Boolean taskStatus;

    private String picture;

    private Plan plan;

    private User user;

    private String planId;

    
    public TaskResponseUser(String id,int priority ,String title, String detail, Date dateTime, Location location,
            Boolean taskStatus, String picture, Plan plan, User user,String planId) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.detail = detail;
        this.dateTime = dateTime;
        this.location = location;
        this.taskStatus = taskStatus;
        this.picture = picture;
        this.plan = plan;
        this.user = user;
        this.planId = planId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getPlanId() {
        return this.planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean isTaskStatus() {
        return this.taskStatus;
    }

    public Boolean getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Plan getPlan() {
        return this.plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}