package com.senior.plan2task.PlanService.Plan;

import com.senior.plan2task.PlanService.Filter.TokenAuthenticationService;
import com.senior.plan2task.PlanService.Model.Member;
import com.senior.plan2task.PlanService.Model.PlanResponse;
import com.senior.plan2task.PlanService.User.User;
import com.senior.plan2task.PlanService.User.UserAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlanController {
    
    @Autowired
    private UserAdapter userAdapter;
    
    @Autowired
    private PlanService planService;
    
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
       
    @GetMapping ("/plan")
    public ResponseEntity<List<PlanResponse>> getPlanByUser(HttpServletRequest request){
       String userId = tokenAuthenticationService.getUserByToken(request);
       List<Plan> plan = planService.getPlanByUser(userId);
       List<PlanResponse> planResponses = new ArrayList<>();
       if(!plan.isEmpty()){
           for (int i = 0; i < plan.size(); i++) {
               List<User> member = new ArrayList<>();
               if(!plan.get(i).getMember().isEmpty()) {
                   for (int j = 0; j < plan.get(i).getMember().size(); j++) {
                       member.add(userAdapter.getUserById(request, plan.get(i).getMember().get(j).getUserId()));
                   }
                   planResponses.add(new PlanResponse(plan.get(i).getId(), plan.get(i).getTitle(), plan.get(i).getDetail(), plan.get(i).getStartDate(), plan.get(i).getEndDate(), plan.get(i).getLocation(), member, plan.get(i).getType(), plan.get(i).isStatus(), plan.get(i).getNotification(), plan.get(i).getUserId()));
               }else {
                   planResponses.add(new PlanResponse(plan.get(i).getId(), plan.get(i).getTitle(), plan.get(i).getDetail(), plan.get(i).getStartDate(), plan.get(i).getEndDate(), plan.get(i).getLocation(), null, plan.get(i).getType(), plan.get(i).isStatus(), plan.get(i).getNotification(), plan.get(i).getUserId()));
               }
           }
           return new ResponseEntity<>(planResponses, HttpStatus.OK);
       }else{
           return new ResponseEntity<>(null, HttpStatus.OK);
       }
    }

    @PostMapping ("/plan/startDate")
    public ResponseEntity<List<Plan>> getPlanByStartDate(HttpServletRequest request, LocalDate startDate) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<Plan> plan = planService.getPlanByStartDate(startDate,userId);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
    @PostMapping ("/plan")
    public ResponseEntity<Plan> createPlan(HttpServletRequest request, Plan plan) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        plan.setUserId(userId);
        plan.setStatus(false);
        List<Member> members = new ArrayList<>();
        members.add(new Member(userId));
        if(!plan.getMember().isEmpty()){
            for (int i = 0; i < plan.getMember().size(); i++) {
                members.add(plan.getMember().get(i));
            }
            plan.setMember(members);
        }else {
            plan.setMember(members);
        }
        planService.createPlan(plan);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
    @PutMapping("/plan/{id}")
    public ResponseEntity<Plan> editPlan(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(id);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
    @DeleteMapping("/plan/{id}")
    public ResponseEntity<Plan> deletePlan(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(id);
        if(userId.equals(plan.getUserId())){
            if(plan.isStatus()==false){
                return new ResponseEntity<>(plan, HttpStatus.OK);
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This plan has been completed and cannot be deleted !!!");
            }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You do not have permission to delete this plan !!!");
        }
    }
    
}
