package com.senior.plan2task.PlanService.Task;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.senior.plan2task.PlanService.Filter.TokenAuthenticationService;
import com.senior.plan2task.PlanService.Model.TaskRequest;
import com.senior.plan2task.PlanService.Model.TaskRequestEdit;
import com.senior.plan2task.PlanService.Plan.Plan;
import com.senior.plan2task.PlanService.Plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class TaskController {
    
    @Autowired
    private PlanService planService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    
    @GetMapping ("/task/plan/id/{id}")
    public ResponseEntity<List<Task>> getTaskById(HttpServletRequest request, @PathVariable String id){
       String userId = tokenAuthenticationService.getUserByToken(request);
       Plan plan = planService.getPlanById(id);
       if(plan.getUserId().equals(userId)){
            List<Task> task = taskService.getTaskByPlan(plan.getId());
            return new ResponseEntity<>(task, HttpStatus.OK);
       } else {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You do not have permission to view the plan !!!");
       }
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(HttpServletRequest request, @RequestBody TaskRequest taskRequest) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(taskRequest.getPlan());
        if(plan!=null && plan.getUserId().equals(userId)){
            List<Task> taskData = taskService.getTaskByPlan(plan.getId());
            Task task = new Task( null, taskData.size()+1, taskRequest.getTitle(), taskRequest.getDetail(), taskRequest.getDate(), taskRequest.getTime(), taskRequest.getLocation(), userId, false, taskRequest.getPicture(), plan.getId());
            taskService.saveTask(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This plan is not in the system or you do not have the right to add plans !!!");
       }
    }

    @PutMapping("/task")
    public ResponseEntity<Task> editTask(HttpServletRequest request, @RequestBody TaskRequestEdit taskRequestEdit) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Task task = taskService.getTaskById(taskRequestEdit.getId());
        if (userId.equals(task.getUserId())) {
            if (task.getTaskStatus()== false) {
                task.setPriority(taskRequestEdit.getPriority());
                task.setTitle(taskRequestEdit.getTitle());
                task.setDetail(taskRequestEdit.getDetail());
                task.setDate(taskRequestEdit.getDate());
                task.setTime(taskRequestEdit.getTime());
                task.setLocation(taskRequestEdit.getLocation());
                task.setTaskStatus(taskRequestEdit.isTaskStatus());
                taskService.saveTask(task);
                return new ResponseEntity<>(task, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This task is complete and cannot be modified !!!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to modify this task !!!");
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> deleteTask(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Task task = taskService.getTaskById(id);
        if (userId.equals(task.getUserId())) {
            if (task.getTaskStatus()== false) {
                taskService.deleteTask(id);
                return new ResponseEntity<>(task, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This task has been completed and cannot be deleted !!!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You do not have permission to delete this task !!!");
        }
    }

}