package com.example.SpringDemoMavenApplication.ServiceImpl;

import com.example.SpringDemoMavenApplication.Model.Task;
import com.example.SpringDemoMavenApplication.Model.TaskDTO;
import com.example.SpringDemoMavenApplication.Repository.TaskRepository;
import com.example.SpringDemoMavenApplication.Service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> fetchAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
