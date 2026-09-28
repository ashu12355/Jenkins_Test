package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "Name Can't be Empty")
    private String name;

    @NotBlank(message = "Email Can't be Empty")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotNull(message = "Salary is Required")
    @Positive(message = "Salary Must be Greater Than 0")
    private Double salary;

    @NotBlank(message = "Department Can't be Empty")
    private String department;
}
