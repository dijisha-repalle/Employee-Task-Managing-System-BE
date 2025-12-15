package com.example.SpringDemoMavenApplication.ServiceImpl;

import com.example.SpringDemoMavenApplication.Exception.EmployeeNotFoundexception;
import com.example.SpringDemoMavenApplication.Exception.TaskAssignmentException;
import com.example.SpringDemoMavenApplication.Exception.TaskNotFoundException;
import com.example.SpringDemoMavenApplication.Model.*;
import com.example.SpringDemoMavenApplication.Repository.EmployeeRepository;
import com.example.SpringDemoMavenApplication.Repository.TaskRepository;
import com.example.SpringDemoMavenApplication.Service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.example.SpringDemoMavenApplication.Constants.EmployeeConstants.*;

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

    @Override
    public Task assignTask(Long taskId, AssignTaskRequest request) {
        // Fetch entities first
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND + taskId));

        Employee assignedTo = employeeRepository.findById(request.getAssignedTo())
                .orElseThrow(() -> new EmployeeNotFoundexception(ASSIGNEE_NOT_FOUND));

        Employee assignedBy = employeeRepository.findById(request.getAssignedBy())
                .orElseThrow(() -> new EmployeeNotFoundexception(ASSIGNER_NOT_FOUND));

        // Validate in logical order
        validateAssignment(assignedBy, assignedTo, task);

        // Perform assignment
        task.setAssignedTo(assignedTo);
        task.setAssignedBy(assignedBy);
        task.setStatus(TaskStatus.IN_PROGRESS);

        return taskRepository.save(task);
    }

    private void validateAssignment(Employee assignedBy, Employee assignedTo, Task task) {
        // 1. Check assigner permissions
        if (!Set.of(Role.MANAGER, Role.ADMIN).contains(assignedBy.getRole())) {
            throw new TaskAssignmentException(ONLY_MANAGER_OR_ADMIN_CAN_ASSIGN);
        }

        // 2. Check assignee role
        if (assignedTo.getRole() != Role.EMPLOYEE) {
            throw new TaskAssignmentException(ONLY_ASSIGN_TO_EMPLOYEE);
        }

        // 3. Check self-assignment
        if (assignedBy.getId().equals(assignedTo.getId())) {
            throw new TaskAssignmentException(NO_SELF_ASSIGNMENT);
        }

        // 4. Check double assignment
        if (task.getAssignedTo() != null) {
            throw new TaskAssignmentException(TASK_ALREADY_ASSIGNED);
        }

        // 5. Check department restriction for managers
        if (assignedBy.getRole() == Role.MANAGER &&
                !assignedBy.getDepartment().equals(assignedTo.getDepartment())) {
            throw new TaskAssignmentException(MANAGER_DEPARTMENT_RESTRICTION);
        }
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

    /**
     * Delete Task
     * @param TaskId
     */
    public void DeteleTask(Long TaskId) {
        taskRepository.deleteById(TaskId);
    }


}
