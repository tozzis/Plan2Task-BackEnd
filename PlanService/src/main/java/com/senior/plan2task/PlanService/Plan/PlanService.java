package com.senior.plan2task.PlanService.Plan;

import com.senior.plan2task.PlanService.User.User;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class PlanService {
 @Autowired
    private PlanRepository planRepository;
 
    public List<Plan> findAllPlan() {
        return planRepository.findAll();
    }
    public List<Plan> getPlanByUser(User id){
        return planRepository.findPlanByUser(id);
    }
    
    public List<Plan> findPlanByStartDate(Date startDate) {
        return planRepository.findPlanByStartDate(startDate);
    }
    public List<Plan> getPlanByStartDate(Date startDate){
        return planRepository.findPlanByStartDate(startDate);
    }
    public void createPlan(Plan plan){
         planRepository.save(plan);
    }
    
}
