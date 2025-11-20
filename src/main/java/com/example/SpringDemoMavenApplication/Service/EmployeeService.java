package com.example.SpringDemoMavenApplication.Service;

import com.example.SpringDemoMavenApplication.Model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee emp);

    List<Employee> getAllEmployes();

    Optional<Employee> getByID(Long empId);

    Employee updtaeEmployee(Employee emp);

    void deleteEmployee(Long empId);

    List<Employee> getByDeptId(String deptId);

    Page<Employee> getEmployeesWithPagination(int page, int size, String sortBy);

}
