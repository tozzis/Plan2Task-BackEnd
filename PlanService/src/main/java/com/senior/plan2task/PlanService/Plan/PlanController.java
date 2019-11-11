package com.senior.plan2task.PlanService.Plan;

import com.senior.plan2task.PlanService.Filter.TokenAuthenticationService;
import com.senior.plan2task.PlanService.Model.PlanRequestEdit;
import com.senior.plan2task.PlanService.Model.PlanResponse;
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
import org.springframework.web.bind.annotation.RequestBody;
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
       
    @GetMapping ("/plans")
    public ResponseEntity<List<PlanResponse>> getPlanByUser(HttpServletRequest request){
       String userId = tokenAuthenticationService.getUserByToken(request);
       List<Plan> plan = planService.getPlanByUser(userId);
       if(!plan.isEmpty()){
            List<PlanResponse> planResponses = new ArrayList<>();
            for (int i = 0; i < plan.size(); i++) {
                planResponses.add(new PlanResponse(plan.get(i).getId(), plan.get(i).getTitle(), plan.get(i).getDetail(), plan.get(i).getStartDate(), plan.get(i).getEndDate(), plan.get(i).getLocation(), plan.get(i).getType(), plan.get(i).isStatus(), userAdapter.getUserById(request, plan.get(i).getUserId()), plan.get(i).getImage()));
            }
           return new ResponseEntity<>(planResponses, HttpStatus.OK);
       }else{
           return new ResponseEntity<>(null, HttpStatus.OK);
       }
    }

    @GetMapping ("/plans/today")
    public ResponseEntity<List<Plan>> getPlanByLocaldateToday(HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<Plan> plan = planService.getPlanByLocaldateToday(userId);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @GetMapping ("/plans/startdate/{startDate}")
    public ResponseEntity<List<Plan>> getPlanByStartdate(HttpServletRequest request, @PathVariable String startDate) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        LocalDate startDateLocalDate = LocalDate.parse(startDate);
        List<Plan> plan = planService.getPlanByStartDate(startDateLocalDate, userId);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
    @GetMapping ("/plan/{id}")
    public ResponseEntity<Plan> getPlanById(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(id);
        if(userId.equals(plan.getUserId())){
            return new ResponseEntity<>(plan, HttpStatus.OK);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This plan could not open !!!");
        }
    }
    
    @PostMapping ("/plan")
    public ResponseEntity<Plan> createPlan(HttpServletRequest request, @RequestBody Plan plan) {
        if(plan.getStartDate().isAfter(plan.getEndDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This plan could not add because StartDate must be before EndDate !!!");
        }else {
            String userId = tokenAuthenticationService.getUserByToken(request);
            plan.setUserId(userId);
            plan.setStatus(false);
            plan.setImage("https://cdn.lynda.com/course/186109/186109-636330511862047280-16x9.jpg");
            planService.savePlan(plan);
            return new ResponseEntity<>(plan, HttpStatus.OK);
        }
    }
    
    @PutMapping("/plan")
    public ResponseEntity<Plan> editPlan(HttpServletRequest request, @RequestBody PlanRequestEdit planRequestEdit) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(planRequestEdit.getId());
        if(userId.equals(plan.getUserId())){
            if(plan.isStatus()==false){
                if(plan.getStartDate().isAfter(plan.getEndDate())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This plan could not add because StartDate must be before EndDate !!!");
                }else {
                    plan.setTitle(planRequestEdit.getTitle());
                    plan.setDetail(planRequestEdit.getDetail());
                    plan.setStartDate(planRequestEdit.getStartDate());
                    plan.setEndDate(planRequestEdit.getEndDate());
                    plan.setType(planRequestEdit.getType());
                    plan.setStatus(planRequestEdit.isStatus());
                    if(planRequestEdit.getLocation() != null){
                        plan.setLocation(planRequestEdit.getLocation());
                    }
                    planService.savePlan(plan);
                    return new ResponseEntity<>(plan, HttpStatus.OK);
                }
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This plan is complete and cannot be modified !!!");
            }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to modify this plan !!!");
        }
    }
    
    @PutMapping("/plan/image")
    public ResponseEntity<Plan> editPlanImage(HttpServletRequest request, @RequestBody Plan planRequestEdit) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(planRequestEdit.getId());
        if(userId.equals(plan.getUserId())){
            plan.setImage(planRequestEdit.getImage());
            planService.savePlan(plan);
            return new ResponseEntity<>(plan, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to modify this plan !!!");
        }
    }
    
    @PutMapping("/plan/status/{id}")
    public ResponseEntity<Plan> editPlanStatus(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(id);
        if(userId.equals(plan.getUserId())){
            plan.setStatus(!plan.isStatus());
            planService.savePlan(plan);
            return new ResponseEntity<>(plan, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to modify this plan !!!");
        }
    }
    
    @DeleteMapping("/plan/{id}")
    public ResponseEntity<Plan> deletePlan(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(id);
        if(userId.equals(plan.getUserId())){
            if(plan.isStatus()==false){
                planService.deletePlan(id);
                return new ResponseEntity<>(plan, HttpStatus.OK);
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This plan has been completed and cannot be deleted !!!");
            }
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You do not have permission to delete this plan !!!");
        }
    }
    
}
