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

    /**
     * Get the list of tasks
     *
     * @return list of tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> task = taskService.fetchAllTasks();
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    /**
     * create a task
     *
     * @param task CreateTaskRequestDTO
     * @return tasks
     */
    @PostMapping
    public ResponseEntity<Task> createTsk(@RequestBody CreateTaskRequestDTO task) {
        Task empTask = taskService.createTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * List of Unassigned task
     *
     * @return Unassigned tasks list
     */
    @GetMapping("/unassigned")
    public ResponseEntity<List<Task>> getUnassignedTasks() {
        List<Task> tasks = taskService.getUnassignedTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }



    /**
     * Updates or assign the task to employee by manager or admin
     *
     * @param taskId  Long
     * @param request AssignTaskRequest
     * @return assigned task to employee
     */
    @PutMapping("/assign/{taskId}")
    public ResponseEntity<Task> assignTask(@PathVariable Long taskId, @RequestBody AssignTaskRequest request) {
        Task task = taskService.assignTask(taskId, request);
        return new ResponseEntity<>(task, HttpStatus.CREATED);

    }

    /**
     * Get list of tasks assigned to particular user
     *
     * @param employeeId
     * @return list of tasks
     */
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Task>> getEmployeeTasks(@PathVariable Long employeeId) {
        List<Task> tasks = taskService.getTasksForEmployee(employeeId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * Get list of tasks assigned by manager
     *
     * @param managerId Long
     * @return list of task assigned by manager
     */
    @GetMapping("/assigned-by/{managerId}")
    public ResponseEntity<List<Task>> getTasksAssignedByManager(@PathVariable Long managerId) {
        List<Task> tasks = taskService.getTasksAssignedBy(managerId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTaskFromList(@PathVariable Long taskId) {
        taskService.DeteleTask(taskId);
        return new ResponseEntity<>("Task Deleted Successfully", HttpStatus.OK);
    }


}
