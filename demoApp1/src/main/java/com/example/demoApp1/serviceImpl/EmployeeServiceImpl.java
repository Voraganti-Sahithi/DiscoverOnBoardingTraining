package com.example.demoApp1.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demoApp1.dao.EmployeeRepository;
import com.example.demoApp1.dto.EmployeeDTO;
import com.example.demoApp1.eo.Employee;
import com.example.demoApp1.exceptions.EmployeeNotFoundException;
import com.example.demoApp1.exceptions.EmployeeValidationException;
import com.example.demoApp1.mapperclass.EmployeeMapper;
import com.example.demoApp1.service.EmployeeService;

@Service
@Validated
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private EmployeeMapper employeeMapper; 
	

	@Override
	public List<EmployeeDTO> getAllEmployees(){
		List<Employee> employees = employeeRepo.findAll();
		if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found.");
        }
		return employees.stream()
				.map(employeeMapper::employeeToEmployeeDTO)
				.collect(Collectors.toList());
	}

	public EmployeeDTO getEmployeeById(Long id) {
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
		return employeeMapper.employeeToEmployeeDTO(employee);
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		
		if (employeeDTO.getName() == null || employeeDTO.getName().trim().isEmpty()) {
	        throw new EmployeeValidationException("Name is mandatory and cannot be blank.");
	    }
	    if (employeeDTO.getName().length() < 2 || employeeDTO.getName().length() > 50) {
	        throw new EmployeeValidationException("Name must be between 2 and 50 characters.");
	    }

	    // Validate Age
	    if (employeeDTO.getAge() < 18 || employeeDTO.getAge() > 65) {
	        throw new EmployeeValidationException("Age must be between 18 and 65.");
	    }
		
		Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
		Employee savedEmployee = employeeRepo.save(employee);
		return employeeMapper.employeeToEmployeeDTO(savedEmployee);
		// TODO Auto-generated method stub
	}

	@Override
	public List<EmployeeDTO> getEmployeeByName(String name) {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepo.findByName(name);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employees found with name: " + name);
        }
        return employees.stream()
                        .map(employeeMapper::employeeToEmployeeDTO)
                        .collect(Collectors.toList());
	}

}
