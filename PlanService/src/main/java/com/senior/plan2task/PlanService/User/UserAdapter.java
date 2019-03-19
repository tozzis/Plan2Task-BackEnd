package com.senior.plan2task.PlanService.User;

import static com.senior.plan2task.PlanService.Filter.GlobalValue.userService;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAdapter {
    
    public User getUser(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String url = userService+"/user";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", request.getHeader("Authorization"));
        HttpEntity<String> entity = new HttpEntity<>("Authorization", headers);
        User user = restTemplate.exchange(url, HttpMethod.GET, entity, User.class).getBody();
        return user;
    }
    
}
