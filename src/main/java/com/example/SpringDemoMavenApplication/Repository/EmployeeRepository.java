package com.example.SpringDemoMavenApplication.Repository;

import com.example.SpringDemoMavenApplication.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByDepartment(String department);
}
