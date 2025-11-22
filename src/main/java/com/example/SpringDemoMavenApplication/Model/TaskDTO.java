package com.example.SpringDemoMavenApplication.Model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class TaskDTO {

    private String title;
    private String description;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
