package com.senior.plan2task.PlanService.Plan;

import com.senior.plan2task.PlanService.User.User;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;


public class PlanController {
    @Autowired
    private PlanService planService;
    
    @GetMapping ("/plans")
    public ResponseEntity<List<Plan>> findAllPlan() {
        List<Plan> plan = planService.findAllPlan();
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
    @GetMapping ("/plans/user")
    public ResponseEntity<List<Plan>> getPlanByUser(User userId){
       List<Plan> plan = planService.getPlanByUser(userId);
       return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @PostMapping ("/plan/startDate")
    public ResponseEntity<List<Plan>> getPlanByStartDate(Date startDate) {
        List<Plan> plan = planService.getPlanByStartDate(startDate);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
   
    
    
}
