package com.senior.plan2task.PlanService.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskRequestEdit {
    private String id;

    private int priority;

    private String title;

    private String detail;

    private LocalDate date;

    private LocalTime time;

    private Location location;

    private Boolean taskStatus;

    public TaskRequestEdit() {
    }

    public TaskRequestEdit(String id, int priority, String title, String detail, LocalDate date, LocalTime time,
            Location location, Boolean taskStatus) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.time = time;
        this.location = location;
        this.taskStatus = taskStatus;
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

    public Boolean isTaskStatus() {
        return this.taskStatus;
    }

    public Boolean getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

}