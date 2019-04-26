package com.senior.plan2task.PlanService.Task;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task getTaskById(String id) {
        return taskRepository.findById(id).get();
    }
    
    public List<Task> getTaskByPlan(String plan) {
        return taskRepository.findTaskByPlan(plan);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
    
}
