package com.example.demo.service;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Employee;
import com.example.demo.exception.DuplicateEmailException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    //save employee to db

    public Employee createEmployee(EmployeeRequest employeeRequest){

        if(employeeRepository.existsByEmail(employeeRequest.getEmail())){
            throw new DuplicateEmailException("Email Already Exist");
        }

        Employee employee = new Employee();
       employee.setName(employeeRequest.getName());
       employee.setSalary(employeeRequest.getSalary());
       employee.setDepartment(employeeRequest.getDepartment());
       employee.setEmail(employeeRequest.getEmail());

       return employeeRepository.save(employee);
    }

    // get All employees

    public Page<Employee> getAllEmployees(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    //get Employee by Id
    public Employee getEmployeeById(Long id) {
        return  employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee Not Found with id : "+id));
    }

    //delete employee

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    //get Employee By Dept

    public List<Employee> getEmployeeByDepartment(String department){
        return employeeRepository.findByDepartment(department);
    }

    //update Employee
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setName(employee.getName());

        return employeeRepository.save(existingEmployee);
    }
}
