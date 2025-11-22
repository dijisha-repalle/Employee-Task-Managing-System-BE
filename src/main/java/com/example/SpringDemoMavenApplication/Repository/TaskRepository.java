package com.example.SpringDemoMavenApplication.Repository;

import com.example.SpringDemoMavenApplication.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByAssignedToIsNull();
    List<Task> findByAssignedToId(Long employeeId);
    List<Task> findByAssignedById(Long managerId);

}
