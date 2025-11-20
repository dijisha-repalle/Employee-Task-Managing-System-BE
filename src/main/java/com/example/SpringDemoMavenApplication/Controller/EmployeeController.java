package com.example.SpringDemoMavenApplication.Controller;

import com.example.SpringDemoMavenApplication.Model.Employee;
import com.example.SpringDemoMavenApplication.ServiceImpl.EmployeeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

   @PostMapping
   public ResponseEntity<Employee> createUser(@RequestBody Employee emp){
        Employee employee=employeeService.createEmployee(emp);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList=employeeService.getAllEmployes();
        return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.OK);
    }


   @GetMapping("/{empId}")
   public ResponseEntity<Optional<Employee>> findByEmployeeId(@PathVariable Long empId){
        Optional<Employee> employee=employeeService.getByID(empId);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmp(@PathVariable Long empId){
        employeeService.deleteEmployee(empId);
        return new ResponseEntity<>("Employee successfully deleted",HttpStatus.OK);
    }


    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmp(@PathVariable Long empId,
                                           @RequestBody Employee emp){
        emp.setId(empId);
        Employee updatedEmp = employeeService.updtaeEmployee(emp);
        return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
    }

    @GetMapping("/dept")
    public ResponseEntity<List<Employee>> findEmployeeByDept(@RequestParam String department){
        List<Employee> employee=employeeService.getByDeptId(department);
        return new ResponseEntity<>(employee,HttpStatus.OK);

    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Employee>> getEmployeesPaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        Page<Employee> employees = employeeService.getEmployeesWithPagination(page, size, sortBy);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}
