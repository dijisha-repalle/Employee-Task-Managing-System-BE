package com.example.SpringDemoMavenApplication.Service;

import com.example.SpringDemoMavenApplication.Model.Employee;
import com.example.SpringDemoMavenApplication.Model.Task;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskAlertScheduler {

    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final EmailService emailService;  // or SlackService

    public TaskAlertScheduler(TaskService taskService, EmployeeService employeeService, EmailService emailService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.emailService = emailService;
    }

    // Runs every 5 hours (e.g., 00:00, 05:00, 10:00, 15:00, 20:00)
    @Scheduled(cron = "0 */2 * * * *")
    public void sendIdleResourcesAlert() {

        List<Task> unassignedTasks = taskService.getUnassignedTasks();
        List<Employee> freeEmployees = employeeService.getEmployeeWithNoTask();

        // If nothing to report → skip sending email
        if (unassignedTasks.isEmpty() && freeEmployees.isEmpty()) {
            return;
        }

        // Build plain-text message
        StringBuilder message = new StringBuilder();
        message.append("Task & Workforce Alert - ").append(LocalDateTime.now()).append("\n\n");

        message.append("Unassigned tasks: ").append(unassignedTasks.size()).append("\n");
        if (!unassignedTasks.isEmpty()) {
            message.append("Pending tasks:\n");
            unassignedTasks.forEach(t -> message.append("  • ").append(t.getTitle()).append(" (Priority: ").append(t.getPriority()).append(")\n"));
        }

        message.append("\nEmployees with no tasks: ").append(freeEmployees.size()).append("\n");
        if (!freeEmployees.isEmpty()) {
            message.append("Available employees:\n");
            freeEmployees.forEach(e -> message.append("  • ").append(e.getName()).append(" (").append(e.getDepartment()).append(")\n"));
        }

        message.append("\nPlease assign tasks to keep everyone productive!");

        // Get all managers + admins
        List<String> recipients = employeeService.getManagersAndAdminsEmails();
        System.out.println("Sending email to: " + employeeService.getManagersAndAdminsEmails());
        // Send simple plain-text email
        emailService.sendPlainTextEmail(recipients, "Task Alert: Idle Tasks & Free Employees", message.toString());

        // Optional: Send to Slack channel instead
        // slackService.sendMessage("#task-alerts", message.toString());
    }
}
