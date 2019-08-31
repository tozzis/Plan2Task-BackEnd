package com.senior.plan2task.PlanService.Plan;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanRepository extends MongoRepository<Plan, String>{
    public List<Plan> findPlanByUserId(String userId);
    public List<Plan> findPlanByStartDateLessThanEqualAndEndDateGreaterThanEqualAndUserId(LocalDate startDate, LocalDate endDate, String userId);
    public List<Plan> findPlanByStartDateAndUserId(LocalDate startDate, String userId);   
   
}