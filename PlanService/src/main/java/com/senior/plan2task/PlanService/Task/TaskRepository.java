package com.senior.plan2task.PlanService.Task;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String>{
    public List<Task> findTaskByPlanOrderByPriorityAsc(String plan);
    public List<Task> findTaskByUserId(String userId);

}