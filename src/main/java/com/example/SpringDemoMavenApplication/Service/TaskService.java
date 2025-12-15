package com.example.SpringDemoMavenApplication.Service;

import com.example.SpringDemoMavenApplication.Model.AssignTaskRequest;
import com.example.SpringDemoMavenApplication.Model.CreateTaskRequestDTO;
import com.example.SpringDemoMavenApplication.Model.Task;

import java.util.List;

public interface TaskService {
    List<Task> fetchAllTasks();

    Task createTask(CreateTaskRequestDTO task);

    List<Task> getUnassignedTasks();

    Task assignTask(Long taskId, AssignTaskRequest request);

    List<Task> getTasksForEmployee(Long employeeId);

    List<Task> getTasksAssignedBy(Long managerId);


    void DeteleTask(Long TaskId);





}
