package com.java.springboot.neo4j.application.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.neo4j.application.data.Employee;
import com.java.springboot.neo4j.application.service.EmployeeSearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee Heirarchy", description = "Spring Boot + Neo4j application for Employee serach in Organization Heirarchy")
public class EmployeeSearchController {

	@Autowired
	private EmployeeSearchService employeeSearchService;

	@ApiOperation(value = "Search employee by employee's name")
	@GetMapping("/searchby/name/{name}")
	public List<Employee> findByName(@PathVariable("name") String name) {
		return employeeSearchService.getEmployeeByName(name);
	}

	@ApiOperation(value = "List all employees")
	@GetMapping("/list")
	public List<Employee> findAllEmployee() {
		return employeeSearchService.getAll();
	}

	@ApiOperation(value = "Get employee details by empId")
	@GetMapping("/searchby/empId/{empId}")
	public Employee findByEmpId(@PathVariable("empId") Long empId) {
		return employeeSearchService.findByEmpId(empId);
	}

	@ApiOperation(value = "List all employees by city")
	@GetMapping("/searchby/city/{city}")
	public List<Employee> findByCity(@PathVariable("city") String city) {
		return employeeSearchService.findByCity(city);
	}

	@ApiOperation(value = "List employees based on gender")
	@GetMapping("/searchby/gender/{gender}")
	public List<Employee> findByGender(@PathVariable("gender") String gender) {
		return employeeSearchService.findByGender(gender);
	}

	@ApiOperation(value = "Add a new employee and relationship to an existing employee")
	@PutMapping("/add/new")
	public String addEmployee(@RequestBody Employee employee, Long toEmpId) {
		return employeeSearchService.addEmployee(employee, toEmpId);
	}

	@ApiOperation(value = "Add relationship for an existing employee to existing supervisor")
	@PostMapping("/add/relationship/for/reportees/to/supervisor")
	public String addRelationshipTo(@RequestBody Map<Long, String> reportees, Long SupervisorEmpId) {
		return addRelationshipToSupervisor(reportees.keySet(), SupervisorEmpId);
	}

	@ApiOperation(value = "Delete an employee and its associated relationship from heirarchy")
	@PostMapping("/delete/employee")
	public String deleteEmployee(@RequestBody Map<Long, String> reportees) {
		return deleteEmployee(reportees.keySet());
	}

	private String addRelationshipToSupervisor(Set<Long> reporteesEmpId, Long supervisorEmpId) {
		boolean flag = Boolean.FALSE;
		String reponseStr = StringUtils.EMPTY;
		Iterator<Long> itr = reporteesEmpId.iterator();
		int i = 0;
		while (itr.hasNext()) {
			reponseStr = employeeSearchService.addRelationshipTo(itr.next(), supervisorEmpId);
			i++;
			if (i == reporteesEmpId.size())
				flag = Boolean.TRUE;
		}
		return flag
				? String.format(
						"Relationship- \"%s\" has been created for reportees empId's : %s to supervisor's empId: %s",
						reponseStr, StringUtils.join(reporteesEmpId.toString(), ", "), supervisorEmpId.toString())
				: "Request not processed.";
	}

	private String deleteEmployee(Set<Long> reporteesEmpId) {
		boolean flag = Boolean.FALSE;
		Iterator<Long> itr = reporteesEmpId.iterator();
		int i = 0;
		while (itr.hasNext()) {
			employeeSearchService.deleteEmployee(itr.next());
			i++;
			if (i == reporteesEmpId.size())
				flag = Boolean.TRUE;
		}
		return flag ? String.format("Employee(s)- \"%s\" has been deleted from heirarchy",
				StringUtils.join(reporteesEmpId.toString(), ", ")) : "Request not processed.";
	}

}
