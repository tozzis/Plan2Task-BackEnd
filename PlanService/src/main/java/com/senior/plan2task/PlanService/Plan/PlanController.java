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
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity <User> gettest(HttpServletRequest request){
       User eiei = userAdapter.getUserById(request,"5c9314088b3f1eca120149c8");
       //User eiei = new User();
        return new ResponseEntity<>(eiei, HttpStatus.OK);
    }
       
    @GetMapping ("/plan")
    public ResponseEntity<List<PlanResponse>> getPlanByUser(HttpServletRequest request){
       String userId = tokenAuthenticationService.getUserByToken(request);
       List<Plan> plan = planService.getPlanByUser(userId);
       List<PlanResponse> planResponses = new ArrayList<>();
       if(!plan.isEmpty()){
           for (int i = 0; i < plan.size(); i++) {
               List<User> member = new ArrayList<>();
               if(!plan.get(i).getMember().isEmpty()) {
                   for (int j = 0; i < plan.get(i).getMember().size(); j++) {
                       String test = plan.get(i).getMember().get(j).getUserId();
                       User eiei = userAdapter.getUserById(request, test);
                       member.add(eiei);
                   }
                   PlanResponse planList = new PlanResponse(plan.get(i).getId(), plan.get(i).getTitle(), plan.get(i).getDetail(), plan.get(i).getStartDate(), plan.get(i).getEndDate(), plan.get(i).getLocation(), member, plan.get(i).getType(), plan.get(i).isStatus(), plan.get(i).getNotification(), plan.get(i).getUserId());
                   planResponses.add(planList);
               }else{
                   PlanResponse planList = new PlanResponse(plan.get(i).getId(), plan.get(i).getTitle(), plan.get(i).getDetail(), plan.get(i).getStartDate(), plan.get(i).getEndDate(), plan.get(i).getLocation(), null, plan.get(i).getType(), plan.get(i).isStatus(), plan.get(i).getNotification(), plan.get(i).getUserId());
                   planResponses.add(planList);
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
        List<Member> members = new ArrayList<>();
        members.add(new Member(userId));
        if(plan.getMember()==null){
            plan.setMember(members);
        }
        planService.createPlan(plan);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
}
