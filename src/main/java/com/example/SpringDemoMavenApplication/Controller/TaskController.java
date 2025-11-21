package com.example.SpringDemoMavenApplication.Controller;

import com.example.SpringDemoMavenApplication.Model.Task;
import com.example.SpringDemoMavenApplication.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> task=taskService.fetchAllTasks();
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task>  createTsk(@RequestBody Task task){
        Task empTask=taskService.createTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
