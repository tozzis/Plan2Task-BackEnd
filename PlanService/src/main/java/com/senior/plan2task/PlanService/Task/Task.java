package com.senior.plan2task.PlanService.Task;

import com.senior.plan2task.PlanService.Model.Location;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    private int priority;

    private String title;

    private String detail;

    private Date dateTime;

    private Location location;

    private String userId;

    private Boolean taskStatus;

    private String picture;

    private String plan;

    public Task() {
    }

    public Task(String id, int priority, String title, String detail, Date dateTime, Location location, String userId, Boolean taskStatus, String picture, String plan) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.detail = detail;
        this.dateTime = dateTime;
        this.location = location;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
