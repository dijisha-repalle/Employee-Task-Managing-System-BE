package com.example.SpringDemoMavenApplication.Service;

import com.example.SpringDemoMavenApplication.Model.Task;

import java.util.List;

public interface TaskService {
    List<Task> fetchAllTasks();

    Task createTask(Task task);




}
