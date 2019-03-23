package com.senior.plan2task.PlanService.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.senior.plan2task.PlanService.Plan.Plan;
import com.senior.plan2task.PlanService.User.User;

public class TaskResponse {
    private String id;

    private int priority;

    private String title;

    private String detail;

    private LocalDate date;

    private LocalTime time;

    private Location location;

    private User user;

    private Boolean taskStatus;

    private List<Plan> plan;

    public TaskResponse() {
    }

    public TaskResponse(String id, int priority, String title, String detail, LocalDate date, LocalTime localTime,
            Location location, User user, Boolean taskStatus, List<Plan> plan) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.time = localTime;
        this.location = location;
        this.user = user;
        this.taskStatus = taskStatus;
        this.plan = plan;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Plan> getPlan() {
        return this.plan;
    }

    public void setPlan(List<Plan> plan) {
        this.plan = plan;

    }
}