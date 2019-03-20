package com.senior.plan2task.UserService.User;

import com.senior.plan2task.UserService.Facebook.AuthenFacebookAdapter;
import com.senior.plan2task.UserService.Facebook.FacebookAccount;
import com.senior.plan2task.UserService.Filter.TokenAuthenticationService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenFacebookAdapter authenFacebookAdapter;
    
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> user = userService.findAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping("/user")
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        String id = tokenAuthenticationService.getUserByToken(request);
        System.out.println("id :"+id);
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping("/user/fb/signin")
    public ResponseEntity<HashMap> signInByFacebook(HttpServletRequest request) {  
        String tokenFacebook = request.getHeader("facebook");
        FacebookAccount facebookAccount = authenFacebookAdapter.getFacebookAccount(tokenFacebook);
        User user = userService.getUserByEmail(facebookAccount.getEmail());
        HashMap<String, Object> responseData = new HashMap();
        
        if (user != null) {
            String token = tokenAuthenticationService.createTokenUser(user);
            responseData.put("Plan2Task", token);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            if(facebookAccount.getEmail()==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "email not found !!!");
            }else{
                User fb_create = new User(null, facebookAccount.getEmail(), facebookAccount.getFirstName(), facebookAccount.getLastName(), facebookAccount.getGender(), null, facebookAccount.getPicture().getData().getUrl());
                userService.createUser(fb_create);
                User fb_sign = userService.getUserByEmail(facebookAccount.getEmail());
                if (fb_sign != null) {
                        String token = tokenAuthenticationService.createTokenUser(fb_sign);
                        responseData.put("Plan2Task", token);
                        return new ResponseEntity<>(responseData, HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "มีบางอย่างผิดพลาด !!!");
                }
            }
        }
    }
    
    @PostMapping("/user/id")
    public ResponseEntity<User> getUserById(@RequestBody Map<String, String> userId) {
        User user = userService.getUserById(userId.get("id"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("/user/email")
    public ResponseEntity<User> getUserByEmail(@RequestBody Map<String, String> userEmail) {
        User user = userService.getUserByEmail(userEmail.get("email"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}