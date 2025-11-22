package com.example.SpringDemoMavenApplication.ServiceImpl;

import com.example.SpringDemoMavenApplication.Model.*;
import com.example.SpringDemoMavenApplication.Repository.EmployeeRepository;
import com.example.SpringDemoMavenApplication.Repository.TaskRepository;
import com.example.SpringDemoMavenApplication.Service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final EmployeeRepository employeeRepository;

    public TaskServiceImpl(TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get all Task
     *
     * @return
     */
    @Override
    public List<Task> fetchAllTasks() {
        return taskRepository.findAll();
    }

    /***
     * create Task
     * @param task
     * @return
     */
    @Override
    public Task createTask(CreateTaskRequestDTO task) {
        Task task1 = new Task();
        task1.setTitle(task.getTitle());
        task1.setDescription(task.getDescription());
        task1.setDueDate(task.getDueDate());
        task1.setStatus(TaskStatus.TODO);

        // New task starts unassigned
        task1.setAssignedTo(null);
        task1.setAssignedBy(null);

        return taskRepository.save(task1);

    }

    /**
     *  GET UNASSIGNED TASKS
     * @return
     */
    @Override
    public List<Task> getUnassignedTasks() {
        return taskRepository.findByAssignedToIsNull();
    }

    /**
     * ASSIGN TASK TO EMPLOYEE
     * @param taskId
     * @param request
     * @return
     */
    @Override
    public Task assignTask(Long taskId, AssignTaskRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));

        Employee assignedTo = employeeRepository.findById(request.getAssignedTo())
                .orElseThrow(() -> new RuntimeException("Assigned-to employee does not exist"));

        Employee assignedBy = employeeRepository.findById(request.getAssignedBy())
                .orElseThrow(() -> new RuntimeException("Assigned-by user does not exist"));

        // 1️⃣ Only MANAGER or ADMIN can assign tasks
        if (!(assignedBy.getRole().equals(Role.MANAGER) || assignedBy.getRole().equals(Role.ADMIN))) {
            throw new RuntimeException("Only MANAGER or ADMIN can assign tasks");
        }

        // 2️⃣ You cannot assign a task to MANAGER or ADMIN
        if (!(assignedTo.getRole().equals(Role.EMPLOYEE))) {
            throw new RuntimeException("Task can only be assigned to EMPLOYEE");
        }

        // 3️⃣ Prevent self assignment
        if (assignedBy.getId().equals(assignedTo.getId())) {
            throw new RuntimeException("Users cannot assign tasks to themselves");
        }

        // 4️⃣ Prevent double assignment
        if (task.getAssignedTo() != null) {
            throw new RuntimeException("Task is already assigned");
        }

        // 5️⃣ Everything is valid → assign
        task.setAssignedTo(assignedTo);
        task.setAssignedBy(assignedBy);
        task.setStatus(TaskStatus.IN_PROGRESS);

        // 6️⃣ Manager can assign tasks ONLY to their department
        if (assignedBy.getRole().equals(Role.MANAGER)) {
            if (!assignedBy.getDepartment().equals(assignedTo.getDepartment())) {
                throw new RuntimeException("Manager can assign tasks only within their department");
            }
        }

        return taskRepository.save(task);
    }


    /**
     * GET TASKS FOR EMPLOYEE
     * @param employeeId
     * @return
     */
    @Override
    public List<Task> getTasksForEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee not found");
        }

        return taskRepository.findByAssignedToId(employeeId);
    }

    /**
     * GET TASKS ASSIGNED BY A MANAGER/ADMIN
     * @param managerId
     * @return
     */
    @Override
    public List<Task> getTasksAssignedBy(Long managerId) {
        if (!employeeRepository.existsById(managerId)) {
            throw new RuntimeException("Manager not found");
        }

        return taskRepository.findByAssignedById(managerId);
    }
}
