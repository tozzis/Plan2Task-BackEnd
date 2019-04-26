package com.senior.plan2task.PlanService.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskRequest {
    
    private String id;

    private String title;

    private String detail;

    private LocalDate date;
    
    private LocalTime time;

    private Location location;

    private Boolean taskStatus;

    private String picture;

    private String plan;

    public TaskRequest() {
    }

    public TaskRequest(String id, String title, String detail, LocalDate date, LocalTime time, Location location, Boolean taskStatus, String picture, String plan) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.time = time;
        this.location = location;
        this.taskStatus = taskStatus;
        this.picture = picture;
        this.plan = plan;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
    
}
