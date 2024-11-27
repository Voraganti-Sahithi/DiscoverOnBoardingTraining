package com.example.demoApp1.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demoApp1.dao.EmployeeRepository;
import com.example.demoApp1.dto.EmployeeDTO;
import com.example.demoApp1.eo.Employee;
import com.example.demoApp1.mapperclass.EmployeeMapper;
import com.example.demoApp1.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private EmployeeMapper employeeMapper; 
	

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		return employees.stream()
				.map(employeeMapper::employeeToEmployeeDTO)
				.collect(Collectors.toList());
	}

	public EmployeeDTO getEmployeeById(Long id) {
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
		return employeeMapper.employeeToEmployeeDTO(employee);
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
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
            throw new RuntimeException("No employees found with name: " + name);
        }
        return employees.stream()
                        .map(employeeMapper::employeeToEmployeeDTO)
                        .collect(Collectors.toList());
	}

}
