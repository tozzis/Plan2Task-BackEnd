package com.senior.plan2task.PlanService.Task;

import java.util.List;
import com.senior.plan2task.PlanService.Plan.Plan;
import com.senior.plan2task.PlanService.Plan.PlanService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PlanService planService;

    public List<Task> getTaskByUser(String id) {
        return taskRepository.findTaskByUserId(id);
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).get();
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public List<Plan> getPlanByUser(String id) {
        return planService.getPlanByUser(id);
    }

    public List<Task> getTaskByTaskStatus(Boolean taskStatus, String userId) {
        return taskRepository.findTaskByTaskStatus(taskStatus, userId);
    }
    
}
