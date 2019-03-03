package com.senior.plan2task.UserService.Facebook;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenFacebookAdapter {
    
    public FacebookAccount getFacebookAccount(String accessToken) {
    RestTemplate restTemplate = new RestTemplate();
    String graphFacebook = "https://graph.facebook.com/v3.2/me";
    String fields = "id,email,first_name,last_name,gender,picture.width(150).height(150)";
    String url = String.format("%s?fields=%s&access_token=%s", graphFacebook, fields, accessToken);
    return restTemplate.getForObject(String.format(url) , FacebookAccount.class);
  }

}