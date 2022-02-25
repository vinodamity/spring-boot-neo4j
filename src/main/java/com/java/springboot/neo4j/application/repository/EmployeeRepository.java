package com.java.springboot.neo4j.application.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.springboot.neo4j.application.data.Employee;

/**
 * Employee Hierarchy repository with having CRUD operations
 * 
 * @author VinodSingh
 *
 */
@Repository
public interface EmployeeRepository extends Neo4jRepository<Employee, Long> {

	/**
	 * Find employee(s) by name This work for partial name search along with
	 * ignore cases
	 * 
	 * @param name
	 * @return
	 */
	List<Employee> findByNameContainsIgnoreCase(String name);

	/**
	 * Find all employee list from org's hierarchy
	 */
	List<Employee> findAll();

	/**
	 * Find an employee by empId
	 * 
	 * @param empId
	 * @return
	 */
	Employee findByEmpId(Long empId);

	/**
	 * Find employee(s) by location(city)
	 * 
	 * @param city
	 * @return
	 */
	List<Employee> findByCityContainsIgnoreCase(String city);

	/**
	 * Find employee(s) by gender
	 * 
	 * @param gender
	 * @return
	 */
	List<Employee> findByGenderIgnoreCase(String gender);

	/**
	 * Save an employee along with details
	 */
	@SuppressWarnings("unchecked")
	Employee save(Employee employee);

	/**
	 * Create relationship for an existing employee to another existing employee
	 * 
	 * @param empId
	 *            new employee
	 * @param toEmpId
	 *            supervisor
	 * @return
	 */
	@Query("" + "MATCH (e1:Employee) WITH e1 " + "MATCH (e2:Employee) where e1.empId = $empId AND e2.empId = $toEmpId "
			+ "CREATE (e1)-[r:REPORT_TO]->(e2) return type(r)")
	String createRelationship(@Param("empId") Long empId, @Param("toEmpId") Long toEmpId);

	/**
	 * Delete the employee and its relationship
	 * 
	 * @param empId
	 */
	@Query("" + "MATCH (e:Employee {empId:$empId}) " + "DETACH " + "DELETE e")
	void deleteEmployeeWithAllRelationship(@Param("empId") Long empId);
}