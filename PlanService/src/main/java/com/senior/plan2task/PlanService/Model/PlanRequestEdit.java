package com.senior.plan2task.PlanService.Model;

import java.time.LocalDate;

public class PlanRequestEdit {
    
    private String id;
    
    private String title;
    
    private String detail;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private Location location;
    
    private String type;
    
    private boolean status;

    public PlanRequestEdit() {
    }

    public PlanRequestEdit(String id, String title, String detail, LocalDate startDate, LocalDate endDate, Location location, String type, boolean status) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.type = type;
        this.status = status;
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

}
