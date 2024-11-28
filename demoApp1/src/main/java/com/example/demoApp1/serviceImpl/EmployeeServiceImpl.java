package com.example.demoApp1.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demoApp1.bo.EmployeeBO;
import com.example.demoApp1.dao.EmployeeRepository;
import com.example.demoApp1.dto.EmployeeDTO;
import com.example.demoApp1.exceptions.EmployeeNotFoundException;
import com.example.demoApp1.exceptions.EmployeeValidationException;
import com.example.demoApp1.mapperclass.EmployeeMapper;
import com.example.demoApp1.service.EmployeeService;
import com.example.demoApp1.vo.Employee;

@Service
@Validated
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeBO employeeBo;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employee) {
		// TODO Auto-generated method stub
		return employeeBo.createEmployee(employee);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		// TODO Auto-generated method stub
		
		return employeeBo.getAllEmployees();
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return employeeBo.getEmployeeById(id);
	}

	@Override
	public List<EmployeeDTO> getEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return employeeBo.getEmployeeByName(name);
	}
}
