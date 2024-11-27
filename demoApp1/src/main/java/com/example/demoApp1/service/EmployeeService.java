package com.example.demoApp1.service;

import java.util.List;
import java.util.Optional;

import com.example.demoApp1.dto.EmployeeDTO;
import com.example.demoApp1.eo.Employee;

public interface EmployeeService {
	 EmployeeDTO createEmployee(EmployeeDTO employee);
	 List<EmployeeDTO> getAllEmployees();
	 EmployeeDTO getEmployeeById(Long id);
	 List<EmployeeDTO> getEmployeeByName(String name);
}
