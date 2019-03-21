/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.plan2task.PlanService.User;

import java.util.Date;
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
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author New_pc
 */
public class PlanController {
    @Autowired
    private PlanService planService;
    
    @GetMapping ("/plans")
    public ResponseEntity<List<Plan>> findAllPlan() {
        List<Plan> plan = planService.findAllPlan();
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
        /*@GetMapping ("/plans/user")
        public ResponseEntity<Plan> getPlanByUser(RequestBody Map<String,String> user.userId){
            return null;
*/
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
