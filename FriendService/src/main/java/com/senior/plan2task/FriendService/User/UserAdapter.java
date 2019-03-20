package com.senior.plan2task.FriendService.User;

import static com.senior.plan2task.FriendService.Filter.GlobalValue.userService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAdapter {
    
    public User getUserById(HttpServletRequest request, String id){
        RestTemplate restTemplate = new RestTemplate();
        String url = userService+"/user/id";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", request.getHeader("Authorization"));
        HttpEntity<UserRequest> entity = new HttpEntity<>(new UserRequest(id), headers);
        User user = restTemplate.exchange(url, HttpMethod.POST, entity, User.class).getBody();
        return user;
    }
    
}
