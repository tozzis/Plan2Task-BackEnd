/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.plan2task.PlanService.User;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author New_pc
 */
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
