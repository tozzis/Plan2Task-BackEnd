package com.senior.plan2task.PlanService.Model;

public class Location {
    
    private String name;
    
    private String nameFull;
    
    private Double lat;
    
    private Double lng;

    public Location() {
    }

    public Location(String name, String nameFull, Double lat, Double lng) {
        this.name = name;
        this.nameFull = nameFull;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
    
}
