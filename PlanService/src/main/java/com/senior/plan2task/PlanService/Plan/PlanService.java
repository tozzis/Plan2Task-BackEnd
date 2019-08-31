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
    
    public List<Plan> getPlanByLocaldateToday(String userId){
        return planRepository.findPlanByStartDateLessThanEqualAndEndDateGreaterThanEqualAndUserId(LocalDate.now(), LocalDate.now(), userId);
    }
    public List<Plan> getPlayByStartdate (LocalDate startDate,String userId){
        return planRepository.findPlanByStartDateAndUserId(startDate,userId);
    }
    
    public Plan getPlanById(String id){
        return planRepository.findById(id).get();
    }
    
    public void savePlan(Plan plan){
         planRepository.save(plan);
    }
    
    public void deletePlan(String id){
        planRepository.deleteById(id);
    }
    
}
