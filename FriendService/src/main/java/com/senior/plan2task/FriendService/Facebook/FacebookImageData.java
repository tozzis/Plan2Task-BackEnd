package com.senior.plan2task.FriendService.Facebook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacebookImageData {
    
    @JsonProperty("url")
    private String url;
    
    @JsonProperty("height")
    private int height;
    
    @JsonProperty("width")
    private int width;
    
    @JsonProperty("is_silhouette")
    private boolean is_silhouette;

    public FacebookImageData() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isIs_silhouette() {
        return is_silhouette;
    }

    public void setIs_silhouette(boolean is_silhouette) {
        this.is_silhouette = is_silhouette;
    }

    @Override
    public String toString() {
        return "FacebookImageData{" + "url=" + url + ", height=" + height + ", width=" + width + ", is_silhouette=" + is_silhouette + '}';
    }
    
}