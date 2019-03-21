package com.senior.plan2task.PlanService.Plan;

import com.senior.plan2task.PlanService.Filter.TokenAuthenticationService;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlanController {
    
    @Autowired
    private PlanService planService;
    
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    
    
    @GetMapping ("/plan/user")
    public ResponseEntity<List<Plan>> getPlanByUser(HttpServletRequest request){
       String userId = tokenAuthenticationService.getUserByToken(request);
       List<Plan> plan = planService.getPlanByUser(userId);
       return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @PostMapping ("/plan/startDate")
    public ResponseEntity<List<Plan>> getPlanByStartDate(HttpServletRequest request, LocalDate startDate) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<Plan> plan = planService.getPlanByStartDate(startDate,userId);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
}
