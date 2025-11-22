package com.example.SpringDemoMavenApplication.Controller;

import com.example.SpringDemoMavenApplication.Model.AssignTaskRequest;
import com.example.SpringDemoMavenApplication.Model.CreateTaskRequestDTO;
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
    public ResponseEntity<Task>  createTsk(@RequestBody CreateTaskRequestDTO task){
        Task empTask=taskService.createTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/unassigned")
    public ResponseEntity<List<Task>> getUnassignedTasks() {
        return ResponseEntity.ok(taskService.getUnassignedTasks());
    }

    @PutMapping("/assign/{taskId}")
    public ResponseEntity<Task> assignTask(@PathVariable Long taskId,
                                           @RequestBody AssignTaskRequest request) {
        return ResponseEntity.ok(taskService.assignTask(taskId, request));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Task>> getEmployeeTasks(@PathVariable Long employeeId) {
        return ResponseEntity.ok(taskService.getTasksForEmployee(employeeId));
    }

    @GetMapping("/assigned-by/{managerId}")
    public ResponseEntity<List<Task>> getTasksAssignedByManager(@PathVariable Long managerId) {
        return ResponseEntity.ok(taskService.getTasksAssignedBy(managerId));
    }



}
