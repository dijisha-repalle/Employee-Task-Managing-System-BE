package com.example.SpringDemoMavenApplication.Repository;

import com.example.SpringDemoMavenApplication.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

}
