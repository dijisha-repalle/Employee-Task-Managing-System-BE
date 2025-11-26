package com.example.SpringDemoMavenApplication.Support;

import com.example.SpringDemoMavenApplication.Model.CreateTaskRequestDTO;
import com.example.SpringDemoMavenApplication.Model.Task;
import com.example.SpringDemoMavenApplication.Model.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

//    public Task toEntity(CreateTaskRequestDTO dto) {
//        return Task.builder()
//                .title(dto.getTitle())
//                .description(dto.getDescription())
//                .dueDate(dto.getDueDate())
//                .status(TaskStatus.TODO)
//                .assignedTo(null)  // New tasks start unassigned
//                .assignedBy(null)
//                .build();
//    }
//
//    public TaskResponseDTO toDto(Task task) {
//        return TaskResponseDTO.builder()
//                .id(task.getId())
//                .title(task.getTitle())
//                .description(task.getDescription())
//                .dueDate(task.getDueDate())
//                .status(task.getStatus())
//                .assignedTo(task.getAssignedTo() != null ? task.getAssignedTo().getId() : null)
//                .assignedBy(task.getAssignedBy() != null ? task.getAssignedBy().getId() : null)
//                .createdAt(task.getCreatedAt())
//                .updatedAt(task.getUpdatedAt())
//                .build();
//    }
}
