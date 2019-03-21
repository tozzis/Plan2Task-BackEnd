package com.senior.plan2task.PlanService.Plan;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    
    @Autowired
    private PlanRepository planRepository;
    
    public List<Plan> getPlanByUser(String id){
        return planRepository.findPlanByUserId(id);
    }
    
    public List<Plan> getPlanByStartDate(LocalDate startDate, String userId){
        return planRepository.findPlanByStartDateAndUserId(startDate,userId);
    }
    public void createPlan(Plan plan){
         planRepository.save(plan);
    }
    
}