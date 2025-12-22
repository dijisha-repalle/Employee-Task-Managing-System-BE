package com.example.SpringDemoMavenApplication.Constants;

import com.example.SpringDemoMavenApplication.Model.Role;

import java.util.Set;

public class EmployeeConstants {
    public static final String EMPLOYEE_DOEN_NOT_EXIST = "Employee not found";

    public static final String DEPARTMENT_MIS_MATCH="Employee or Admin department does not match with the Employee";

    public static final String TASK_ASSIGNMENT_ERROR="Only admin or manager can assign the task to employee";




    // Error messages
    public static final String TASK_NOT_FOUND = "Task not found with id: ";
    public static final String ASSIGNEE_NOT_FOUND = "Assigned-to employee not found with id: ";
    public static final String ASSIGNER_NOT_FOUND = "Assigned-by employee not found with id: ";

    public static final String ONLY_MANAGER_OR_ADMIN_CAN_ASSIGN = "Only MANAGER or ADMIN can assign tasks";
    public static final String ONLY_ASSIGN_TO_EMPLOYEE = "Task can only be assigned to EMPLOYEE";
    public static final String NO_SELF_ASSIGNMENT = "Users cannot assign tasks to themselves";
    public static final String TASK_ALREADY_ASSIGNED = "Task is already assigned";
    public static final String MANAGER_DEPARTMENT_RESTRICTION = "Manager can assign tasks only within their department";
    public static final String EMPLOYEE_INACTIVE = "Cannot assign task to inactive employee";
    public static final String ASSIGNER_INACTIVE = "Inactive user cannot assign tasks";
    public static final String TASK_NOT_PENDING = "Can only assign pending tasks";

    // Validation groups
    public static final Set<Role> ALLOWED_ASSIGNER_ROLES = Set.of(Role.MANAGER, Role.ADMIN);
    public static final Role ALLOWED_ASSIGNEE_ROLE = Role.EMPLOYEE;




}
