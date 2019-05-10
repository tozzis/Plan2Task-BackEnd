package com.senior.plan2task.PlanService.Model;

import java.util.Date;

public class TaskRequestEdit {
    private String id;

    private int priority;

    private String title;

    private String detail;

    private Date dateTime;

    private Location location;

    private Boolean taskStatus;

    public TaskRequestEdit() {
    }

    public TaskRequestEdit(String id, int priority, String title, String detail, Date dateTime, Location location, Boolean taskStatus) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.detail = detail;
        this.dateTime = dateTime;
        this.location = location;
        this.taskStatus = taskStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

}