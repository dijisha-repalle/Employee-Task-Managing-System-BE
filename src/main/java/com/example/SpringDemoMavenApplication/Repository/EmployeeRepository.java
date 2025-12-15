package com.example.SpringDemoMavenApplication.Repository;

import com.example.SpringDemoMavenApplication.Model.Employee;
import com.example.SpringDemoMavenApplication.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByDepartment(String department);
    @Query("SELECT e FROM Employee e " +
            "WHERE e.role = 'EMPLOYEE' " +
            "AND NOT EXISTS (SELECT t FROM Task t WHERE t.assignedTo = e)")
    List<Employee> findEmployeesWithNoTasks();

    List<Employee> findByRoleIn(List<Role> roles);
}
