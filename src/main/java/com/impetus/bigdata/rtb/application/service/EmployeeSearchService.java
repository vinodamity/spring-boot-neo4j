package com.impetus.bigdata.rtb.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.bigdata.rtb.application.data.Employee;
import com.impetus.bigdata.rtb.application.repository.EmployeeRepository;

@Service
public class EmployeeSearchService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getEmployeeByName(String name) {
		return employeeRepository.findByName(name);
	}

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

}
