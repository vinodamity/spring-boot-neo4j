package com.java.springboot.neo4j.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.springboot.neo4j.application.data.Employee;
import com.java.springboot.neo4j.application.repository.EmployeeRepository;

@Service
public class EmployeeSearchService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getEmployeeByName(String name) {
		return employeeRepository.findByNameContainsIgnoreCase(name);
	}

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public List<Employee> findByGender(String gender) {
		return employeeRepository.findByGenderIgnoreCase(gender);
	}

	public List<Employee> findByCity(String city) {
		return employeeRepository.findByCityContainsIgnoreCase(city);
	}

	public Employee findByEmpId(Long empId) {
		return employeeRepository.findByEmpId(empId);
	}

	@Transactional
	public String addEmployee(Employee employee, Long toEmpId) {
		employee = employeeRepository.save(employee);
		String result = addRelationshipTo(employee.getEmpId(), toEmpId);
		result = String.format("Relationship- \"%s\" created for reportee's empId:%s to supervisor's empId:%s", result, employee.getEmpId().toString(),
				toEmpId.toString());
		return result;
	}

	public String addRelationshipTo(Long reporteeEmpId, Long toEmpId) {
		return employeeRepository.createRelationship(reporteeEmpId, toEmpId);
	}

	public void deleteEmployee(Long empId) {
		employeeRepository.deleteEmployeeWithAllRelationship(empId);
	}

	public List<Employee> getHierarchyPathBtwEmployee(Long fromEmpId, Long toEmpId) {
		return employeeRepository.getHierarchyPathBtwEmployee(fromEmpId, toEmpId);
	}

	public List<Employee> getDirectReporteeOfAnEmpId(Long empId) {
		return employeeRepository.getDirectReporteeOfAnEmpId(empId);
	}

	public List<Employee> getIndirectReporteeOfAnEmpId(Long empId) {
		return employeeRepository.getIndirectReporteeOfAnEmpId(empId);
	}

	public String updateHierarchyOfEmployee(Long empId, Long existing_supervisorid, Long new_supervisorid) {
		return employeeRepository.updateHierarchyOfEmployee(empId, existing_supervisorid, new_supervisorid);
	}

}
