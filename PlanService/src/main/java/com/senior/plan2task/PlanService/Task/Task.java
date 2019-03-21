package com.senior.plan2task.PlanService.Task;

import com.senior.plan2task.PlanService.Model.Location;
import com.senior.plan2task.PlanService.Plan.Plan;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {
    
    @Id
    private String id;
    
    private int priority;
    
    private String title;
    
    private String detail;
    
    private LocalDate date;
    
    private LocalTime time;
    
    private Location location;
    
    @DBRef(db="plan")
    private Plan plan;

    public Task() {
    }

    public Task(String id, int priority, String title, String detail, LocalDate date, LocalTime time, Location location, Plan plan) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.time = time;
        this.location = location;
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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

}
