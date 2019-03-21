/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.plan2task.PlanService.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 *
 * @author New_pc
 */
public interface PlanRepository extends MongoRepository<Plan, String>{

    public List<Plan> findPlanByUser(User id);

    public List<Plan> findPlanByStartDate(Date startDate);
     
}
