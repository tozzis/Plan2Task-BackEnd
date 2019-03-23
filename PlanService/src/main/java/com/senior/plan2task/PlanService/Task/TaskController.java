package com.senior.plan2task.PlanService.Task;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.senior.plan2task.PlanService.Filter.TokenAuthenticationService;
import com.senior.plan2task.PlanService.Model.TaskRequestEdit;
import com.senior.plan2task.PlanService.Model.TaskResponse;
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
    private UserAdapter userAdapter;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(HttpServletRequest request, @RequestBody Task task) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        task.setUserId(userId);
        task.setTaskStatus(false);
        taskService.saveTask(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getTaskByUser(HttpServletRequest request) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<Task> task = taskService.getTaskByUser(userId);

        if (!task.isEmpty()) {
            List<TaskResponse> taskResponses = new ArrayList<>();
            for (int i = 0; i < task.size(); i++) {
                taskResponses.add(new TaskResponse(task.get(i).getId(), task.get(i).getPriority(),
                        task.get(i).getTitle(), task.get(i).getDetail(), task.get(i).getDate(), task.get(i).getTime(),
                        task.get(i).getLocation(), userAdapter.getUserById(request, task.get(i).getUserId()),
                        task.get(i).isTaskStatus(), taskService.getPlanByUser(task.get(i).getUserId())));
            }

            return new ResponseEntity<>(taskResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/tasks/taskstatus")
    public ResponseEntity<List<Task>> getPlanByStartDate(HttpServletRequest request, Boolean taskStatus) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        List<Task> task = taskService.getTaskByTaskStatus(taskStatus, userId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/task")
    public ResponseEntity<Task> editTask(HttpServletRequest request, @RequestBody TaskRequestEdit taskRequestEdit) {
        String userId = tokenAuthenticationService.getUserByToken(request);
        Task task = taskService.getTaskById(taskRequestEdit.getId());
        if (userId.equals(task.getUserId())) {
            if (task.isTaskStatus() == false) {
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
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "This task is complete and cannot be modified !!!");
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
            if (task.isTaskStatus() == false) {
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