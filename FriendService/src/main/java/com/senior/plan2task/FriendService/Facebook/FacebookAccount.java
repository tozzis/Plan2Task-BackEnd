package com.senior.plan2task.FriendService.Facebook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacebookAccount {
    
    @JsonProperty("id")
    private long facebookId;

    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("picture")
    private FacebookImage picture;

    public FacebookAccount() {
    }

    public long getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(long facebookId) {
        this.facebookId = facebookId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public FacebookImage getPicture() {
        return picture;
    }

    public void setPicture(FacebookImage picture) {
        this.picture = picture;
    }
    
}