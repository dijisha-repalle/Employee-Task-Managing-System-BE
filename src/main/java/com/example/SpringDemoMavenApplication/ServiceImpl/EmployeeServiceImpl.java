package com.example.SpringDemoMavenApplication.ServiceImpl;

import com.example.SpringDemoMavenApplication.Model.Employee;
import com.example.SpringDemoMavenApplication.Repository.EmployeeRepository;
import com.example.SpringDemoMavenApplication.Service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    @Override
    public List<Employee> getAllEmployes() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getByID(Long empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public Employee updtaeEmployee(Employee emp) {
        Employee exeistingEmp=employeeRepository.findById(emp.getId()).get();
        exeistingEmp.setName(emp.getName());
        exeistingEmp.setId(emp.getId());
        exeistingEmp.setDepartment(emp.getDepartment());
        exeistingEmp.setEmail(emp.getEmail());
        Employee upadteEmp=employeeRepository.save(exeistingEmp);
        return upadteEmp;
    }


    @Override
    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public List<Employee> getByDeptId(String deptId) {
        return employeeRepository.findByDepartment(deptId);
    }

    @Override
    public Page<Employee> getEmployeesWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return employeeRepository.findAll(pageable);
    }


}
