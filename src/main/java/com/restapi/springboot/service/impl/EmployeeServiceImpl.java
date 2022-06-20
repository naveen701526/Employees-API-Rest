package com.restapi.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restapi.springboot.exception.ResourceNotFoundException;
import com.restapi.springboot.model.Employee;
import com.restapi.springboot.repository.EmployeeRepository;
import com.restapi.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {

		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//we need to check whether employee with given id exist in DB or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
//		save existing employee details
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		//check whether a employee exists in a DB oR not
		employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "id",id));
		employeeRepository.deleteById(id);
		
	}
	
	

}
