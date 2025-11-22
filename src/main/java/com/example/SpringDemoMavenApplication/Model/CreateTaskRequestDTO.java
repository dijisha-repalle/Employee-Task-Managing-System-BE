package com.example.SpringDemoMavenApplication.Model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
@Data
public class CreateTaskRequestDTO {

    private String title;
    private String description;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
