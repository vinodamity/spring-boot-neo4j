package com.impetus.bigdata.rtb.application.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impetus.bigdata.rtb.application.data.Employee;
import com.impetus.bigdata.rtb.application.service.EmployeeSearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee Heirarchy", description = "Spring Boot + Neo4j application for Employee serach in Organization Heirarchy")
public class EmployeeSearchController {

	@Autowired
	private EmployeeSearchService employeeSearchService;

	@ApiOperation(value = "Search Employee by employee' name")
	@GetMapping("/searchby/name/{name}")
	public List<Employee> findByName(@PathVariable("name") String name) {
		return employeeSearchService.getEmployeeByName(name);
	}

	@ApiOperation(value = "List All Employees")
	@GetMapping("/list")
	public List<Employee> findAllEmployee() {
		return employeeSearchService.getAll();
	}

	/*
	 * @GetMapping("/employee/searchby/empid/{empid}") public Employee
	 * findByEmpId(@PathVariable("empid") String empid) { return
	 * employeeSearchService.fetchEmployeeByEmpId(empid); }
	 * 
	 * @GetMapping("/movie/{title}") public MovieDetailsDto
	 * findByTitle(@PathVariable("title") String title) { return
	 * employeeSearchService.fetchDetailsByTitle(title); }
	 * 
	 * @GetMapping("/movie/{title}") public MovieDetailsDto
	 * findByTitle(@PathVariable("title") String title) { return
	 * employeeSearchService.fetchDetailsByTitle(title); }
	 * 
	 * @GetMapping("/movie/{title}") public MovieDetailsDto
	 * findByTitle(@PathVariable("title") String title) { return
	 * employeeSearchService.fetchDetailsByTitle(title); }
	 * 
	 * @GetMapping("/movie/{title}") public MovieDetailsDto
	 * findByTitle(@PathVariable("title") String title) { return
	 * employeeSearchService.fetchDetailsByTitle(title); }
	 */

}
