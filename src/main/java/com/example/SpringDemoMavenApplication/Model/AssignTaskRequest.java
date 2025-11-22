package com.example.SpringDemoMavenApplication.Model;

import lombok.Data;

@Data
public class AssignTaskRequest {
    private Long assignedTo;
    private Long assignedBy;
}
