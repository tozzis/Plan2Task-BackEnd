package com.senior.plan2task.FriendService.Facebook;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenFacebookAdapter {
    
    public FacebookData getFacebookDataFriend(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String graphFacebook = "https://graph.facebook.com/v3.2/me/friends";
        String fields = "id,first_name,last_name,picture.width(150).height(150)";
        String url = String.format("%s?fields=%s&access_token=%s", graphFacebook, fields, accessToken);
        return restTemplate.getForObject(String.format(url) , FacebookData.class);
    }
    
}