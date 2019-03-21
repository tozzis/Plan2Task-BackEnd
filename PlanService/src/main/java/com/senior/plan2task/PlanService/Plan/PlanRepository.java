package com.senior.plan2task.PlanService.Plan;

import com.senior.plan2task.PlanService.User.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanRepository extends MongoRepository<Plan, String>{
    public List<Plan> findPlanByUser(User id);
    public List<Plan> findPlanByStartDate(Date startDate);   
}
