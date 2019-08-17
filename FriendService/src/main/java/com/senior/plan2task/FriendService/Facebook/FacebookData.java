package com.senior.plan2task.FriendService.Facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FacebookData {
    
    @JsonProperty("data")
    private List<FacebookAccount> facebookAccount;

    public FacebookData() {
    }

    public List<FacebookAccount> getFacebookAccount() {
        return facebookAccount;
    }

    public void setFacebookAccount(List<FacebookAccount> facebookAccount) {
        this.facebookAccount = facebookAccount;
    }
    
}