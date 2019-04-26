package com.senior.plan2task.PlanService.Model;

import com.senior.plan2task.PlanService.Task.Task;
import java.util.List;

public class TaskRequestPriority {
    
    String plan;
    
    List<Task> task;

    public TaskRequestPriority() {
    }

    public TaskRequestPriority(String plan, List<Task> task) {
        this.plan = plan;
        this.task = task;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
    
}
