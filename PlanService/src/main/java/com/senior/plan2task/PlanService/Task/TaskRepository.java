package com.senior.plan2task.PlanService.Task;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String>{
    public List<Task> findTaskByUserId(String userId);
    public List<Task> findTaskByTaskStatus(Boolean taskStatus,String userId);
    public List<Task> findTaskByDate(LocalDate date);




}