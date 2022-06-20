package com.restapi.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.springboot.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
