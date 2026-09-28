package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

//    private final EmployeeService employeeService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.createEmployee(employeeRequest);
    }

    @GetMapping("/getAllEmployee")
    public Page<Employee> getAllEmployee(Pageable pageable) {
        return employeeService.getAllEmployees(pageable);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/delete/")
    public void deleteEmployeeById(@RequestParam(required = true) Long id) {
       employeeService.deleteEmployee(id);
    }

    @GetMapping("/search")
    public List<Employee> getEmployeeByDepartment(@RequestParam(required = true) String department){
        return employeeService.getEmployeeByDepartment(department);
    }

    @PutMapping("/update")
    public Employee updateEmployee(@RequestParam Long id,@RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);
    }
}
