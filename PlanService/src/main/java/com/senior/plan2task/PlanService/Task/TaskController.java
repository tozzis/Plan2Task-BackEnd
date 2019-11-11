package com.senior.plan2task.PlanService.Task;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.senior.plan2task.PlanService.Filter.TokenAuthenticationService;
import com.senior.plan2task.PlanService.Model.TaskRequest;
import com.senior.plan2task.PlanService.Model.TaskRequestEdit;
import com.senior.plan2task.PlanService.Model.TaskRequestPriority;
import com.senior.plan2task.PlanService.Model.TaskResponseUser;
import com.senior.plan2task.PlanService.Plan.Plan;
import com.senior.plan2task.PlanService.Plan.PlanService;
import com.senior.plan2task.PlanService.User.UserAdapter;

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

    @Autowired
    private UserAdapter userAdapter;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseUser>> getTaskByUser(HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<Task> task = taskService.getTaskByUser(userId);
        if (!task.isEmpty()) {
            List<TaskResponseUser> taskResponseUser = new ArrayList<>();
            for (int i = 0; i < task.size(); i++) {
                taskResponseUser.add(new TaskResponseUser(task.get(i).getId(), task.get(i).getPriority(),
                    task.get(i).getTitle(), task.get(i).getDetail(), task.get(i).getDateTime(),
                    task.get(i).getLocation(), task.get(i).getTaskStatus(), task.get(i).getImage(),
                    planService.getPlanById(task.get(i).getPlan()),
                    userAdapter.getUserById(request, task.get(i).getUserId()),task.get(i).getPlan(),userId,planService.getPlanById(task.get(i).getPlan()).getType()
                ));
            }
            return new ResponseEntity<>(taskResponseUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("/task/plan/id/{id}")
    public ResponseEntity<List<Task>> getTaskById(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(id);
        if (plan.getUserId().equals(userId)) {
            List<Task> task = taskService.getTaskByPlan(plan.getId());
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You do not have permission to view the plan !!!");
        }
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(HttpServletRequest request, @RequestBody TaskRequest taskRequest) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(taskRequest.getPlan());
        if (plan != null && plan.getUserId().equals(userId)) {
            List<Task> taskData = taskService.getTaskByPlan(plan.getId());
            Task task = new Task(null, taskData.size() + 1, taskRequest.getTitle(), taskRequest.getDetail(),
                taskRequest.getDateTime(), taskRequest.getLocation(), userId, false, "https://static.makeuseof.com/wp-content/uploads/2017/02/Shared-Task-List-Featured-670x335.jpg",
                plan.getId(),plan.getType()
            );
            taskService.saveTask(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This plan is not in the system or you do not have the right to add plans !!!");
        }
    }

    @PutMapping("/task")
    public ResponseEntity<Task> editTask(HttpServletRequest request, @RequestBody TaskRequestEdit taskRequestEdit) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Task task = taskService.getTaskById(taskRequestEdit.getId());
        Plan plan = planService.getPlanById(task.getPlan());
        if (userId.equals(task.getUserId())) {
            if (plan.isStatus() == false) {
                task.setTitle(taskRequestEdit.getTitle());
                task.setDetail(taskRequestEdit.getDetail());
                task.setDateTime(taskRequestEdit.getDateTime());
                if(taskRequestEdit.getLocation() != null){
                    task.setLocation(taskRequestEdit.getLocation());
                }
                taskService.saveTask(task);
                return new ResponseEntity<>(task, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "This task is complete and cannot be modified !!!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to modify this task !!!");
        }
    }
    
    @PutMapping("/task/image")
    public ResponseEntity<Task> editTaskImage(HttpServletRequest request, @RequestBody Task taskRequestEdit) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Task task = taskService.getTaskById(taskRequestEdit.getId());
        Plan plan = planService.getPlanById(task.getPlan());
        if (userId.equals(task.getUserId())) {
            if (plan.isStatus() == false) {
                task.setImage(taskRequestEdit.getImage());
                taskService.saveTask(task);
                return new ResponseEntity<>(task, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "This task is complete and cannot be modified !!!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to modify this task !!!");
        }
    }

    @PutMapping("/task/status")
    public ResponseEntity<Task> editTaskStatus(HttpServletRequest request,
            @RequestBody TaskRequestEdit taskRequestEdit) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Task task = taskService.getTaskById(taskRequestEdit.getId());
        Plan plan = planService.getPlanById(task.getPlan());
        if (userId.equals(task.getUserId())) {
            if (plan.isStatus() == false) {
                task.setTaskStatus(taskRequestEdit.getTaskStatus());
                taskService.saveTask(task);
                return new ResponseEntity<>(task, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "This task is complete and cannot be modified !!!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to modify this task !!!");
        }
    }

    @PutMapping("/task/priority")
    public ResponseEntity<List<Task>> editTaskPriority(HttpServletRequest request,
            @RequestBody TaskRequestPriority taskRequestPriority) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Plan plan = planService.getPlanById(taskRequestPriority.getPlan());
        if (plan.getUserId().equals(userId)) {
            List<Task> task = taskService.getTaskByPlan(plan.getId());
            for (int i = 0; i < task.size(); i++) {
                for (int j = 0; j < taskRequestPriority.getTask().size(); j++) {
                    if (task.get(i).getId().equals(taskRequestPriority.getTask().get(j).getId())) {
                        task.get(i).setPriority(taskRequestPriority.getTask().get(j).getPriority());
                        break;
                    }
                }
                taskService.saveTask(task.get(i));
            }
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not allowed to modify this task !!!");
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> deleteTask(HttpServletRequest request, @PathVariable String id) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Task task = taskService.getTaskById(id);
        if (userId.equals(task.getUserId())) {
            if (task.getTaskStatus() == false) {
                taskService.deleteTask(id);
                return new ResponseEntity<>(task, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "This task has been completed and cannot be deleted !!!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You do not have permission to delete this task !!!");
        }
    }

}